package br.com.events.band.adapter.repository.jpa;

import br.com.events.band.adapter.repository.QuoteRequestRepository;
import br.com.events.band.data.model.table.quote_request.QuoteRequestStatusType;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuoteRequestJpaRepository extends QuoteRequestRepository, JpaRepository<QuoteRequestTable, String> {


    @Query(
            "SELECT qr FROM QuoteRequestTable qr " +
                    "WHERE qr.band.uuid = :bandUuid AND " +
                    "((:statuses IS NULL) OR (qr.status IN :statuses)) AND " +
                    "((:startDate IS NULL) OR (:startDate <= qr.creationDate)) AND " +
                    "((:startDate IS NULL) OR (:endDate >= qr.creationDate))"
    )
    Page<QuoteRequestTable> findByBandUuid(
            @Param("bandUuid") String bandUuid,
            @Param("statuses") List<QuoteRequestStatusType> statuses,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
}
