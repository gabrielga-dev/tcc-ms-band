package br.com.events.band.business.command.quote.impl;

import br.com.events.band.adapter.repository.QuoteRepository;
import br.com.events.band.data.model.table.quote.QuoteTable;
import br.com.events.band.data.model.table.quote.QuoteTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link SaveQuoteCommandImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SaveQuoteCommandImplTest {

    @InjectMocks
    private SaveQuoteCommandImpl command;

    @Mock
    private QuoteRepository quoteRepository;

    @Test
    @DisplayName("execute - when called return saved quote")
    void executeWhenCalledReturnSavedQuote() {
        var mockedSaved = QuoteTableMock.build();
        when(quoteRepository.save(any(QuoteTable.class))).thenReturn(mockedSaved);

        var toSave = QuoteTableMock.build();
        var saved = command.execute(toSave);

        Assertions.assertNotNull(saved);
        Assertions.assertEquals(mockedSaved, saved);

        verify(quoteRepository, times(1)).save(eq(toSave));
    }
}
