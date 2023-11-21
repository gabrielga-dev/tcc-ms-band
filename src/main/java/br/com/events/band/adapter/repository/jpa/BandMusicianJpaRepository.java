package br.com.events.band.adapter.repository.jpa;

import br.com.events.band.adapter.repository.BandMusicianRepository;
import br.com.events.band.data.model.table.band.BandMusicianTable;
import br.com.events.band.data.model.table.pk.BandMusicianTablePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BandMusicianJpaRepository extends
        BandMusicianRepository, JpaRepository<BandMusicianTable, BandMusicianTablePk> {

    @Modifying
    @Query("delete from BandMusicianTable b where b.band.uuid=:bandUuid AND b.musician.uuid=:musicianUuid")
    void disassociateMusicianFromBand(@Param("bandUuid") String bandUuid, @Param("musicianUuid") String musicianUuid);
}
