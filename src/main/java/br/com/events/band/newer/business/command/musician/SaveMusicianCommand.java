package br.com.events.band.newer.business.command.musician;

import br.com.events.band.newer.adapter.repository.MusicianRepository;
import br.com.events.band.newer.data.model.table.MusicianTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveMusicianCommand {

    private final MusicianRepository musicianRepository;

    public MusicianTable execute(MusicianTable musicianTable){
        return musicianRepository.save(musicianTable);
    }
}
