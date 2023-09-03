package br.com.events.band.application.process.sheetMusic.create;

import br.com.events.band.infrastructure.process.sheetMusic.create.CreateSheetMusicValidation;
import br.com.events.band.infrastructure.process.sheetMusic.create.CreateSheetMusicValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateSheetMusicValidatorImpl implements CreateSheetMusicValidator {

    private final List<CreateSheetMusicValidation> validations;

    @Override
    public void callProcesses(String param) {
        validations.forEach(v -> v.validate(param));
    }
}
