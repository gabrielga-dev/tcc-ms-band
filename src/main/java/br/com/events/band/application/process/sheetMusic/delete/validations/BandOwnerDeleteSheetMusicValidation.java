package br.com.events.band.application.process.sheetMusic.delete.validations;

import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.application.process.exception.MusicNonExistenceException;
import br.com.events.band.domain.io.musicSheet.delete.useCase.in.DeleteSheetMusicUseCaseForm;
import br.com.events.band.domain.repository.SheetMusicRepository;
import br.com.events.band.infrastructure.process.sheetMusic.delete.DeleteSheetMusicValidation;
import br.com.events.band.util.AuthUtil;
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
