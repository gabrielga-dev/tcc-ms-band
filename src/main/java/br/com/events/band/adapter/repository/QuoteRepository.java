package br.com.events.band.adapter.repository;

import br.com.events.band.data.model.table.quote.QuoteTable;

public interface QuoteRepository {

    QuoteTable save(QuoteTable quoteTable);
}
