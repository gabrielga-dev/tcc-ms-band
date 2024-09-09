package br.com.events.band.business.command.quote_request;


import br.com.events.band.adapter.repository.QuoteRequestRepository;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link SaveQuoteRequestCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SaveQuoteRequestCommandTest {

    @InjectMocks
    private SaveQuoteRequestCommand command;

    @Mock
    private QuoteRequestRepository quoteRequestRepository;

    @Test
    @DisplayName("execute - when called return saved result")
    void executeWhenCalledReturnSavedResult() {
        var mockedSaved = QuoteRequestTableMock.build();
        when(quoteRequestRepository.save(any(QuoteRequestTable.class))).thenReturn(mockedSaved);

        var toSave = QuoteRequestTableMock.build();
        var saved = command.execute(toSave);

        Assertions.assertNotNull(saved);
        Assertions.assertEquals(mockedSaved, saved);

        verify(quoteRequestRepository, times(1)).save(toSave);
    }
}
