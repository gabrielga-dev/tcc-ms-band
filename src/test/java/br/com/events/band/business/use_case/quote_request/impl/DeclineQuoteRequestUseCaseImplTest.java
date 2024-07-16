package br.com.events.band.business.use_case.quote_request.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.adapter.feign.MsEventFeign;
import br.com.events.band.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.band.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.quote_request.QuoteRequestNotFound;
import br.com.events.band.data.io.quote_request.request.DeclineQuoteRequestMsEventRequest;
import br.com.events.band.data.model.table.quote_request.QuoteRequestStatusType;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link DeclineQuoteRequestUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class DeclineQuoteRequestUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindQuoteRequestCommand findQuoteRequestCommand;
    @Mock
    private SaveQuoteRequestCommand saveQuoteRequestCommand;
    @Mock
    private MsEventFeign msEventFeign;
    @InjectMocks
    private DeclineQuoteRequestUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when quote request is not found, then throws BandNonExistenceException")
    void executeWhenQuoteRequestIsNotFoundThenThrowsBandNonExistenceException() {
        when(findQuoteRequestCommand.findByUuidOrThrow(anyString())).thenReturn(QuoteRequestTableMock.build());
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING.repeat(2));

        Assertions.assertThrows(
                BandOwnerException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findQuoteRequestCommand, atMostOnce()).findByUuidOrThrow(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(saveQuoteRequestCommand, never()).execute(any(QuoteRequestTable.class));
        verify(msEventFeign, never()).declineQuoteRequest(anyString(), any(DeclineQuoteRequestMsEventRequest.class));
    }

    @Test
    @DisplayName("execute - when quote request is already answered, then throws QuoteRequestNotFound")
    void executeWhenQuoteRequestIsAlreadyAnsweredThenThrowsQuoteRequestNotFound() {
        var quoteRequest = QuoteRequestTableMock.build();
        quoteRequest.setStatus(QuoteRequestStatusType.ANSWERED);
        when(findQuoteRequestCommand.findByUuidOrThrow(anyString())).thenReturn(quoteRequest);
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        Assertions.assertThrows(
                QuoteRequestNotFound.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findQuoteRequestCommand, atMostOnce()).findByUuidOrThrow(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(saveQuoteRequestCommand, never()).execute(any(QuoteRequestTable.class));
        verify(msEventFeign, never()).declineQuoteRequest(anyString(), any(DeclineQuoteRequestMsEventRequest.class));
    }

    @Test
    @DisplayName("execute - when quote request is not answered, then decline quote request")
    void executeWhenQuoteRequestIsNotAnsweredThenDeclineQuoteRequest() {
        var quoteRequest = QuoteRequestTableMock.build();
        quoteRequest.setStatus(QuoteRequestStatusType.NON_ANSWERED);
        when(findQuoteRequestCommand.findByUuidOrThrow(anyString())).thenReturn(quoteRequest);
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(saveQuoteRequestCommand.execute(any(QuoteRequestTable.class))).thenReturn(QuoteRequestTableMock.build());
        doNothing().when(msEventFeign).declineQuoteRequest(anyString(), any(DeclineQuoteRequestMsEventRequest.class));

        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findQuoteRequestCommand, atMostOnce()).findByUuidOrThrow(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(saveQuoteRequestCommand, atMostOnce()).execute(any(QuoteRequestTable.class));
        verify(msEventFeign, atMostOnce()).declineQuoteRequest(anyString(), any(DeclineQuoteRequestMsEventRequest.class));
    }
}
