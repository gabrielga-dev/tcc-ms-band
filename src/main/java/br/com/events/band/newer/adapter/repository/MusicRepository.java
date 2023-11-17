package br.com.events.band.newer.adapter.repository;

import br.com.events.band.newer.data.table.MusicTable;

import java.util.Optional;

public interface MusicRepository {

    MusicTable save(MusicTable music);

    Optional<MusicTable> findByUuidAndBandUuid(String musicUuid, String bandUuid);
}
