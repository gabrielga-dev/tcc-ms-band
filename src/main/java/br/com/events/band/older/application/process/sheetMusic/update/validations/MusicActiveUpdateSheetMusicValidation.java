package br.com.events.band.older.application.process.sheetMusic.update.validations;

import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.exception.SheetMusicNonExistenceException;
import br.com.events.band.older.domain.repository.SheetMusicRepository;
import br.com.events.band.older.infrastructure.process.sheetMusic.update.UpdateSheetMusicValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MusicActiveUpdateSheetMusicValidation implements UpdateSheetMusicValidation {

    private final SheetMusicRepository sheetMusicRepository;

    @Override
    public void validate(String toValidate) {
        var sheet = sheetMusicRepository.findById(toValidate)
                .orElseThrow(SheetMusicNonExistenceException::new);

        if (!sheet.getMusic().getActive()){
            throw new BandNonExistenceException();
        }
    }
}
