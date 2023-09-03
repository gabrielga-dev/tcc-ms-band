package br.com.events.band.application.process.music.delete.validations;

import br.com.events.band.application.process.exception.BandNonExistenceException;
import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.application.process.exception.MusicNonExistenceException;
import br.com.events.band.domain.repository.MusicRepository;
import br.com.events.band.infrastructure.process.music.delete.DeleteMusicValidation;
import br.com.events.band.util.AuthUtil;
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
