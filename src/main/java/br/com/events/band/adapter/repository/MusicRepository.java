package br.com.events.band.adapter.repository;

import br.com.events.band.data.model.table.music.MusicTable;

import java.util.Optional;

public interface MusicRepository {

    MusicTable save(MusicTable music);

    Optional<MusicTable> findByUuidAndBandUuid(String musicUuid, String bandUuid);
}
