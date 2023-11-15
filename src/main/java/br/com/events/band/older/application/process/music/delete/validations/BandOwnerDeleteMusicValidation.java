package br.com.events.band.older.application.process.music.delete.validations;

import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.newer.core.exception.music.MusicNonExistenceException;
import br.com.events.band.older.domain.repository.MusicRepository;
import br.com.events.band.older.infrastructure.process.music.delete.DeleteMusicValidation;
import br.com.events.band.older.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandOwnerDeleteMusicValidation implements DeleteMusicValidation {

    private final MusicRepository musicRepository;

    @Override
    public void validate(String musicUuid) {
        var music = musicRepository.findById(musicUuid).orElseThrow(MusicNonExistenceException::new);

        if (!music.getActive()) {
            throw new MusicNonExistenceException();
        }

        var band = music.getBand();

        if (!band.getActive()) {
            throw new BandNonExistenceException();
        } else if (!band.getOwnerUuid().equals(AuthUtil.getAuthenticatedPerson().getUuid())) {
            throw new BandOwnerException();
        }
    }
}
