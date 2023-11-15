package br.com.events.band.older.application.process.sheetMusic.delete.validations;

import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.exception.SheetMusicNonExistenceException;
import br.com.events.band.older.domain.io.musicSheet.delete.useCase.in.DeleteSheetMusicUseCaseForm;
import br.com.events.band.older.domain.repository.SheetMusicRepository;
import br.com.events.band.older.infrastructure.process.sheetMusic.delete.DeleteSheetMusicValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MusicActiveDeleteSheetMusicValidation implements DeleteSheetMusicValidation {

    private final SheetMusicRepository sheetMusicRepository;

    @Override
    public void validate(DeleteSheetMusicUseCaseForm toValidate) {
        var sheet = sheetMusicRepository.findById(toValidate.getSheetUuid())
                .orElseThrow(SheetMusicNonExistenceException::new);

        if (!sheet.getMusic().getActive()) {
            throw new BandNonExistenceException();
        }
    }
}
