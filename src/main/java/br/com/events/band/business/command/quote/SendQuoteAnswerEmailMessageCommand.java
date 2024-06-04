package br.com.events.band.business.command.quote;

import br.com.events.band.data.model.table.quote.QuoteTable;

public interface SendQuoteAnswerEmailMessageCommand {

    void execute(QuoteTable quote, boolean hired);
}
