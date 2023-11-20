package br.com.events.band.business.command.musician_type;

import br.com.events.band.adapter.repository.MusicianTypeRepository;
import br.com.events.band.data.model.table.musician.MusicianTypeTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindMusicianTypeCommand {

    private final MusicianTypeRepository musicianTypeRepository;

    public List<MusicianTypeTable> byUuid(List<String> uuids) {
        return musicianTypeRepository.findAllByUuid(uuids);
    }
}
