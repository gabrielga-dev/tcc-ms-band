package br.com.events.band.newer.business.command.music;

import br.com.events.band.newer.adapter.repository.MusicRepository;
import br.com.events.band.newer.data.model.table.MusicTable;
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
