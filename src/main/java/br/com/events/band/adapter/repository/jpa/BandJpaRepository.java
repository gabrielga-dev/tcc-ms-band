package br.com.events.band.adapter.repository.jpa;

import br.com.events.band.adapter.repository.BandRepository;
import br.com.events.band.data.model.table.band.BandTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BandJpaRepository extends BandRepository, JpaRepository<BandTable, String> {

    @Query(
            "SELECT band FROM BandTable band JOIN band.address address WHERE " +
                    "(band.ownerUuid = :ownerUuid) AND " +
                    "((:name IS NULL) OR (band.name LIKE CONCAT('%',:name,'%'))) AND " +
                    "((:cityId IS NULL) OR (address.city = :cityId)) AND " +
                    "((:stateIso IS NULL) OR (address.state = :stateIso)) AND " +
                    "((:countryIso IS NULL) OR (address.country = :countryIso)) AND " +
                    "((:creationDateStart IS NULL) OR (:creationDateStart <= band.creationDate)) AND " +
                    "((:creationDateEnd IS NULL) OR (:creationDateEnd >= band.creationDate))"
    )
    Page<BandTable> findByAuthenticatedPerson(
            Pageable pageable,
            @Param("ownerUuid") String ownerUuid,
            @Param("name") String name,
            @Param("cityId") Long cityId,
            @Param("stateIso") String stateIso,
            @Param("countryIso") String countryIso,
            @Param("creationDateStart") LocalDateTime creationDateStart,
            @Param("creationDateEnd") LocalDateTime creationDateEnd
    );

    @Query(
            "SELECT band FROM BandTable band JOIN band.address address WHERE " +
                    "(band.active = :active) AND " +
                    "((:name IS NULL) OR (band.name LIKE CONCAT('%',:name,'%'))) AND " +
                    "((:cityId IS NULL) OR (address.city = :cityId)) AND " +
                    "((:stateIso IS NULL) OR (address.state = :stateIso)) AND " +
                    "((:countryIso IS NULL) OR (address.country = :countryIso))"
    )
    Page<BandTable> findByCriteria(
            Pageable pageable,
            @Param("active") boolean active,
            @Param("name") String name,
            @Param("cityId") Long cityId,
            @Param("stateIso") String stateIso,
            @Param("countryIso") String countryIso
    );

    Optional<BandTable> findByUuidAndOwnerUuid(String uuid, String ownerUuid);


    @Query("SELECT band FROM BandTable band WHERE band.uuid IN :uuids")
    List<BandTable> findAllByUuid(List<String> uuids);

    List<BandTable> findByOwnerUuid(String ownerUuid);
}
