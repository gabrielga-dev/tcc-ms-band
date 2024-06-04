package br.com.events.band.business.command.quote;

import br.com.events.band.data.model.table.quote.QuoteTable;

public interface FindQuoteCommand {

    QuoteTable byUuidOrThrow(String quoteUuid);
}
