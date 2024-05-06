package br.com.events.band.adapter.repository;

import br.com.events.band.data.model.table.band.BandTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BandRepository {

    BandTable save(BandTable bandTable);

    Page<BandTable> findByAuthenticatedPerson(
            Pageable pageable,
            String ownerUuid,
            String name,
            Long cityId,
            String stateIso,
            String countryIso,
            LocalDateTime creationDateStart,
            LocalDateTime creationDateEnd
    );

    Page<BandTable> findByCriteria(
            Pageable pageable,
            boolean active,
            String name,
            Long cityId,
            String stateIso,
            String countryIso
    );

    Optional<BandTable> findById(String bandUuid);

    Optional<BandTable> findByUuidAndOwnerUuid(String uuid, String ownerUuid);

    List<BandTable> findAllByUuid(@Param("uuids") List<String> uuids);
}
