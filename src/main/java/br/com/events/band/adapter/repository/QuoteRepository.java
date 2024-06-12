package br.com.events.band.adapter.repository;

import br.com.events.band.data.model.table.quote.QuoteTable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository {

    QuoteTable save(QuoteTable quoteTable);

    Optional<QuoteTable> findById(String uuid);

    List<QuoteTable> findAll(Specification<QuoteTable> specification);
}
