package br.com.events.band.application.process.sheetMusic.update.validations;

import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.application.process.exception.SheetMusicNonExistenceException;
import br.com.events.band.domain.repository.SheetMusicRepository;
import br.com.events.band.infrastructure.process.sheetMusic.update.UpdateSheetMusicValidation;
import br.com.events.band.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandOwnerUpdateSheetMusicValidation implements UpdateSheetMusicValidation {

    private final SheetMusicRepository sheetMusicRepository;

    @Override
    public void validate(String toValidate) {
        var sheet = sheetMusicRepository.findById(toValidate)
                .orElseThrow(SheetMusicNonExistenceException::new);

        if (!sheet.getMusic().getBand().getOwnerUuid().equals(AuthUtil.getAuthenticatedPerson().getUuid())){
            throw new BandOwnerException();
        }
    }
}
