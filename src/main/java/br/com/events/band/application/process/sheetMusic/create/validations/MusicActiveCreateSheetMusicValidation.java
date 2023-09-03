package br.com.events.band.application.process.sheetMusic.create.validations;

import br.com.events.band.application.process.exception.BandNonExistenceException;
import br.com.events.band.application.process.exception.MusicNonExistenceException;
import br.com.events.band.domain.repository.MusicRepository;
import br.com.events.band.infrastructure.process.sheetMusic.create.CreateSheetMusicValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MusicActiveCreateSheetMusicValidation implements CreateSheetMusicValidation {

    private final MusicRepository musicRepository;

    @Override
    public void validate(String toValidate) {
        var music = musicRepository.findById(toValidate)
                .orElseThrow(MusicNonExistenceException::new);

        if (!music.getActive()){
            throw new BandNonExistenceException();
        }
    }
}
