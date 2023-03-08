package br.com.events.band.domain.repository;

import br.com.events.band.domain.entity.Band;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * This interface makes every needed communication to the database at the band table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface BandRepository extends JpaRepository<Band, String> {

    @Query(
            "SELECT band FROM Band band WHERE " +
            "(band.ownerUuid = :ownerUuid) AND " +
            "((:name IS NULL) OR (band.name LIKE CONCAT('%',:name,'%'))) AND " +
            "((:creationDateStart IS NULL) OR (:creationDateStart <= band.creationDate)) AND " +
            "((:creationDateEnd IS NULL) OR (:creationDateEnd >= band.creationDate))"
    )
    Page<Band> filterAuthenticatedBands(
            Pageable pageable,
            @Param("ownerUuid") String ownerUuid,
            @Param("name") String name,
            @Param("creationDateStart") LocalDateTime creationDateStart,
            @Param("creationDateEnd") LocalDateTime creationDateEnd
    );

    @Query(
            "SELECT band FROM Band band join band.address address WHERE " +
                    "((:name IS NULL) OR (band.name LIKE CONCAT('%',:name,'%'))) " +
                    "AND ((:country IS NULL) OR (address.country = :country)) " +
                    "AND ((:state IS NULL) OR (address.state = :state)) " +
                    "AND ((:city IS NULL) OR (address.city = :city)) " +
                    "AND band.active = true"
    )
    Page<Band> filterBands(
            Pageable pageable,
            @Param("name") String name,
            @Param("city") Long cityId,
            @Param("state") String stateIso,
            @Param("country") String countryIso
    );
}
