package br.com.events.band.business.command.musician_type;

import br.com.events.band.adapter.repository.MusicianTypeRepository;
import br.com.events.band.data.io.musician_type.criteria.MusicianTypeCriteria;
import br.com.events.band.data.model.table.musician.MusicianTypeTable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindMusicianTypeCommand {

    private final MusicianTypeRepository musicianTypeRepository;

    public List<MusicianTypeTable> findAll() {
        return musicianTypeRepository.findAll();
    }

    public List<MusicianTypeTable> byUuid(List<String> uuids) {
        return musicianTypeRepository.findAllByUuid(uuids);
    }

    public Page<MusicianTypeTable> byCriteria(MusicianTypeCriteria criteria, Pageable pageable) {
        return musicianTypeRepository.findByCriteria(criteria.getName(), pageable);
    }
}
