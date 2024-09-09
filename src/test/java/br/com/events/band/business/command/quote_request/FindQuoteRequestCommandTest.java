package br.com.events.band.business.command.quote_request;

import br.com.events.band.MockConstants;
import br.com.events.band.adapter.repository.QuoteRequestRepository;
import br.com.events.band.core.exception.quote_request.QuoteRequestNotFound;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindQuoteRequestCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindQuoteRequestCommandTest {

    @InjectMocks
    private FindQuoteRequestCommand command;

    @Mock
    private QuoteRequestRepository quoteRequestRepository;


    @Test
    @DisplayName("findByUuidOrThrow - when quote request is found then return quote request")
    void findByUuidOrThrowWhenQuoteRequestIsFoundThenReturnQuoteRequest() {
        var quoteRequest = QuoteRequestTableMock.build();
        when(quoteRequestRepository.findById(anyString())).thenReturn(Optional.of(quoteRequest));

        var result = command.findByUuidOrThrow(MockConstants.STRING);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(quoteRequest, result);

        verify(quoteRequestRepository, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("findByUuidOrThrow - when quote request is not found then throw QuoteRequestNotFound")
    void findByUuidOrThrowWhenQuoteRequestIsNotFoundThenThrowQuoteRequestNotFound() {
        when(quoteRequestRepository.findById(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                QuoteRequestNotFound.class,

                () -> command.findByUuidOrThrow(MockConstants.STRING)
        );

        verify(quoteRequestRepository, times(1)).findById(anyString());
    }
}
