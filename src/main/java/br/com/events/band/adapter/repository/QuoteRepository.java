package br.com.events.band.adapter.repository;

import br.com.events.band.data.model.table.quote.QuoteTable;

import java.util.Optional;

public interface QuoteRepository {

    QuoteTable save(QuoteTable quoteTable);

    Optional<QuoteTable> findById(String uuid);
}
