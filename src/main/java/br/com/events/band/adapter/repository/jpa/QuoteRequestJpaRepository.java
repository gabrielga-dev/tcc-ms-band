package br.com.events.band.adapter.repository.jpa;

import br.com.events.band.adapter.repository.QuoteRequestRepository;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRequestJpaRepository extends QuoteRequestRepository, JpaRepository<QuoteRequestTable, String> {
}
