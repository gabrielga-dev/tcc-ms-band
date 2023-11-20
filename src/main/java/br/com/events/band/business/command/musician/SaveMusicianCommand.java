package br.com.events.band.business.command.musician;

import br.com.events.band.adapter.repository.MusicianRepository;
import br.com.events.band.data.model.table.MusicianTable;
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
