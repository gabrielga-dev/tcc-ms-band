package br.com.events.band.business.use_case.quote_request.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindQuoteRequestByUuidUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindQuoteRequestByUuidUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindQuoteRequestCommand findQuoteRequestCommand;
    @InjectMocks
    private FindQuoteRequestByUuidUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when person is not band owner, then throws BandOwnerException")
    void executeWhenPersonIsNotBandOwnerThenThrowsBandOwnerException() {
        var quoteRequest = QuoteRequestTableMock.build();
        quoteRequest.getBand().setOwnerUuid(MockConstants.STRING);
        when(findQuoteRequestCommand.findByUuidOrThrow(anyString())).thenReturn(quoteRequest);
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING.repeat(2));

        Assertions.assertThrows(
                BandOwnerException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findQuoteRequestCommand, atMostOnce()).findByUuidOrThrow(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
    }

    @Test
    @DisplayName("execute - when person is band owner, then return complete brief")
    void executeWhenPersonIsBandOwnerThenReturnCompleteBrief() {
        when(findQuoteRequestCommand.findByUuidOrThrow(anyString())).thenReturn(QuoteRequestTableMock.build());
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        var returned = useCase.execute(MockConstants.STRING);

        Assertions.assertNotNull(returned);

        verify(findQuoteRequestCommand, atMostOnce()).findByUuidOrThrow(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
    }
}
