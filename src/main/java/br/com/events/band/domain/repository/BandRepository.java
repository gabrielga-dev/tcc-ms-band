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
            "((:name IS NULL) OR (band.name LIKE %:name%)) AND " +
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
            "SELECT band FROM Band band join band.address add WHERE " +
                    "((:name IS NULL) OR (band.name LIKE %:name%)) AND " +
                    "(" +
                    "((:latitude IS NULL) AND (:longitude IS NULL) AND (:distance IS NULL)) " +
                    "OR (FUNCTION('ST_Distance_Sphere', FUNCTION('POINT', add.longitude, add.latitude), FUNCTION('POINT', :longitude, :latitude)) <= :distance)" +
                    ") AND ((:country IS NULL) OR (add.country = :country)) " +
                    "AND ((:state IS NULL) OR (add.state = :state)) " +
                    "AND ((:city IS NULL) OR (add.city = :city)) " +
                    "AND band.active = true"
    )
    Page<Band> filterBands(
            Pageable pageable,
            @Param("name") String name,
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("distance") Double distance,
            @Param("country") String country,
            @Param("state") String state,
            @Param("city") String city
    );
}
