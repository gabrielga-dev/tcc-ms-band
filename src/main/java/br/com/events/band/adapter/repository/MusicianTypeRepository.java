package br.com.events.band.adapter.repository;

import br.com.events.band.data.model.table.musician.MusicianTypeTable;

import java.util.List;

public interface MusicianTypeRepository {
    List<MusicianTypeTable> findAllByUuid(List<String> uuids);
}
