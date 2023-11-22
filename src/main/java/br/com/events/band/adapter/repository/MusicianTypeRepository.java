package br.com.events.band.adapter.repository;

import br.com.events.band.data.model.table.musician.MusicianTypeTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MusicianTypeRepository {
    List<MusicianTypeTable> findAllByUuid(List<String> uuids);

    Page<MusicianTypeTable> findByCriteria(String name, Pageable pageable);
}
