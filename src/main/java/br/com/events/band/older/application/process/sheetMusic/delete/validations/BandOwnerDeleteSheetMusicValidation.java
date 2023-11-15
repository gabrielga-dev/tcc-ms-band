package br.com.events.band.older.application.process.sheetMusic.delete.validations;

import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.newer.core.exception.music.MusicNonExistenceException;
import br.com.events.band.older.domain.io.musicSheet.delete.useCase.in.DeleteSheetMusicUseCaseForm;
import br.com.events.band.older.domain.repository.SheetMusicRepository;
import br.com.events.band.older.infrastructure.process.sheetMusic.delete.DeleteSheetMusicValidation;
import br.com.events.band.older.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandOwnerDeleteSheetMusicValidation implements DeleteSheetMusicValidation {

    private final SheetMusicRepository sheetMusicRepository;

    @Override
    public void validate(DeleteSheetMusicUseCaseForm toValidate) {
        var music = sheetMusicRepository.findById(toValidate.getSheetUuid())
                .orElseThrow(MusicNonExistenceException::new)
                .getMusic();

        if (!music.getBand().getOwnerUuid().equals(AuthUtil.getAuthenticatedPerson().getUuid())) {
            throw new BandOwnerException();
        }
    }
}
