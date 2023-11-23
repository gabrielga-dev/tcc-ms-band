package br.com.events.band.adapter.repository;

import br.com.events.band.data.model.table.music.MusicTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MusicRepository {

    MusicTable save(MusicTable music);

    Optional<MusicTable> findByUuidAndBandUuid(String musicUuid, String bandUuid);

    Optional<MusicTable> findById(String musicUuid);

    Page<MusicTable> findByCriteria(String name, String author, String artist, Pageable pageable);
}
