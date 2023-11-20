package br.com.events.band.business.command.music;

import br.com.events.band.adapter.repository.MusicRepository;
import br.com.events.band.data.model.table.music.MusicTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindMusicCommand {

    private final MusicRepository musicRepository;

    public Optional<MusicTable> byUuid(String musicUuid){
        return this.musicRepository.findById(musicUuid);
    }

    public Optional<MusicTable> byUuidAndBandUuid(String musicUuid, String bandUuid){
        return this.musicRepository.findByUuidAndBandUuid(musicUuid, bandUuid);
    }
}
