package br.com.events.band.adapter.repository;

import br.com.events.band.data.model.table.quote_request.QuoteRequestStatusType;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface QuoteRequestRepository {

    QuoteRequestTable save(QuoteRequestTable toSave);

    Page<QuoteRequestTable> findByBandUuid(
            String bandUuid,
            List<QuoteRequestStatusType> statuses,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );
}
