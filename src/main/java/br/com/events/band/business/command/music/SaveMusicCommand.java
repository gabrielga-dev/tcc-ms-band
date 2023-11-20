package br.com.events.band.business.command.music;

import br.com.events.band.data.model.table.music.MusicTable;
import br.com.events.band.adapter.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveMusicCommand {

    private final MusicRepository musicRepository;

    public MusicTable execute(MusicTable music) {
        return musicRepository.save(music);
    }
}
