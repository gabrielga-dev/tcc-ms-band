package br.com.events.band.application.process.sheetMusic.create.validations;

import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.application.process.exception.MusicNonExistenceException;
import br.com.events.band.domain.repository.MusicRepository;
import br.com.events.band.infrastructure.process.sheetMusic.CreateSheetMusicValidation;
import br.com.events.band.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandOwnerCreateSheetMusicValidation implements CreateSheetMusicValidation {

    private final MusicRepository musicRepository;

    @Override
    public void validate(String toValidate) {
        var music = musicRepository.findById(toValidate)
                .orElseThrow(MusicNonExistenceException::new);

        if (!music.getBand().getOwnerUuid().equals(AuthUtil.getAuthenticatedPerson().getUuid())){
            throw new BandOwnerException();
        }
    }
}
