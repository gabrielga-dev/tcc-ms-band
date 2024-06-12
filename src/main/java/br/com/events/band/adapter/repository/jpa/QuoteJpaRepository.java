package br.com.events.band.adapter.repository.jpa;

import br.com.events.band.adapter.repository.QuoteRepository;
import br.com.events.band.data.model.table.quote.QuoteTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteJpaRepository extends QuoteRepository,
        JpaRepository<QuoteTable, String>, JpaSpecificationExecutor<QuoteTable> {
}
