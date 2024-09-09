package br.com.events.band.business.use_case.quote.impl;


import br.com.events.band.business.command.quote.FindQuoteCommand;
import br.com.events.band.business.command.quote.SaveQuoteCommand;
import br.com.events.band.business.command.quote.SendQuoteAnswerEmailMessageCommand;
import br.com.events.band.data.io.quote.message.QuoteEventAnswerMessageMock;
import br.com.events.band.data.model.table.quote.QuoteStatusType;
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
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link HandleQuoteEventAnswerUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class HandleQuoteEventAnswerUseCaseImplTest {

    @Mock
    private FindQuoteCommand findQuoteCommand;
    @Mock
    private SaveQuoteCommand saveQuoteCommand;
    @Mock
    private SendQuoteAnswerEmailMessageCommand sendQuoteAnswerEmailMessageCommand;
    @InjectMocks
    private HandleQuoteEventAnswerUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when called, then update quote")
    void executeWhenCalledThenUpdateQuote() {
        var quote = QuoteTableMock.build();
        when(findQuoteCommand.byUuidOrThrow(anyString())).thenReturn(quote);
        when(saveQuoteCommand.execute(any(QuoteTable.class))).thenReturn(QuoteTableMock.build());
        doNothing().when(sendQuoteAnswerEmailMessageCommand).execute(any(QuoteTable.class), anyBoolean());

        var message = QuoteEventAnswerMessageMock.build();
        Assertions.assertDoesNotThrow(() -> useCase.execute(message));

        Assertions.assertEquals(QuoteStatusType.HIRED, quote.getStatus());

        verify(findQuoteCommand, atMostOnce()).byUuidOrThrow(message.getQuoteUuid());
        verify(saveQuoteCommand, atMostOnce()).execute(quote);
        verify(sendQuoteAnswerEmailMessageCommand, atMostOnce()).execute(any(QuoteTable.class), eq(message.isHired()));
    }
}
