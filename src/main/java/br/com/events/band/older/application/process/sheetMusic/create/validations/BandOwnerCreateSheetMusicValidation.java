package br.com.events.band.older.application.process.sheetMusic.create.validations;

import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.newer.core.exception.music.MusicNonExistenceException;
import br.com.events.band.newer.adapter.repository.jpa.MusicJpaRepository;
import br.com.events.band.older.infrastructure.process.sheetMusic.create.CreateSheetMusicValidation;
import br.com.events.band.older.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandOwnerCreateSheetMusicValidation implements CreateSheetMusicValidation {

    private final MusicJpaRepository musicRepository;

    @Override
    public void validate(String toValidate) {
        var music = musicRepository.findById(toValidate)
                .orElseThrow(MusicNonExistenceException::new);

        if (!music.getBand().getOwnerUuid().equals(AuthUtil.getAuthenticatedPerson().getUuid())){
            throw new BandOwnerException();
        }
    }
}
