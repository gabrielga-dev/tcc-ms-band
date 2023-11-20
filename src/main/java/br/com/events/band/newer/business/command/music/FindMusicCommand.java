package br.com.events.band.newer.business.command.music;

import br.com.events.band.newer.adapter.repository.MusicRepository;
import br.com.events.band.newer.data.model.table.MusicTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindMusicCommand {

    private final MusicRepository musicRepository;

    public Optional<MusicTable> byUuidAndBandUuid(String musicUuid, String bandUuid){
        return this.musicRepository.findByUuidAndBandUuid(musicUuid, bandUuid);
    }
}
