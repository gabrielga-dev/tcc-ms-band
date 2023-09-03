package br.com.events.band.application.process.sheetMusic.update;

import br.com.events.band.infrastructure.process.sheetMusic.update.UpdateSheetMusicValidation;
import br.com.events.band.infrastructure.process.sheetMusic.update.UpdateSheetMusicValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateSheetMusicValidatorImpl implements UpdateSheetMusicValidator {

    private final List<UpdateSheetMusicValidation> validations;

    @Override
    public void callProcesses(String param) {
        validations.forEach(v -> v.validate(param));
    }
}
