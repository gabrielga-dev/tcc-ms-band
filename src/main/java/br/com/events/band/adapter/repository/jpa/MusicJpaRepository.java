package br.com.events.band.adapter.repository.jpa;

import br.com.events.band.adapter.repository.MusicRepository;
import br.com.events.band.data.model.table.music.MusicTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MusicJpaRepository extends MusicRepository, JpaRepository<MusicTable, String> {

    Optional<MusicTable> findByUuidAndContributingBand_Uuid(String musicUuid, String bandUuid);

    default Optional<MusicTable> findByUuidAndBandUuid(String musicUuid, String bandUuid) {
        return this.findByUuidAndContributingBand_Uuid(musicUuid, bandUuid);
    }

    @Query(
            "SELECT music FROM MusicTable music " +
                    "WHERE music.active = true " +
                    "AND ((:name IS NULL) OR  (music.name LIKE CONCAT('%',:name,'%')))" +
                    "AND ((:author IS NULL) OR  (music.author LIKE CONCAT('%',:author,'%')))" +
                    "AND ((:artist IS NULL) OR  (music.artist LIKE CONCAT('%',:artist,'%')))"
    )
    Page<MusicTable> findByCriteria(
            @Param("name") String name,
            @Param("author") String author,
            @Param("artist") String artist,
            Pageable pageable
    );
}
