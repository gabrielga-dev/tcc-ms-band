package br.com.events.band.newer.adapter.repository.jpa;

import br.com.events.band.newer.adapter.repository.MusicRepository;
import br.com.events.band.newer.data.model.table.MusicTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MusicJpaRepository extends MusicRepository, JpaRepository<MusicTable, String> {

    Optional<MusicTable> findByUuidAndContributingBand_Uuid(String musicUuid, String bandUuid);

    default Optional<MusicTable> findByUuidAndBandUuid(String musicUuid, String bandUuid) {
        return this.findByUuidAndContributingBand_Uuid(musicUuid, bandUuid);
    }
}
