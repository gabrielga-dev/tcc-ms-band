package br.com.events.band.application.process.sheetMusic.delete;

import br.com.events.band.domain.io.musicSheet.delete.useCase.in.DeleteSheetMusicUseCaseForm;
import br.com.events.band.infrastructure.process.sheetMusic.delete.DeleteSheetMusicValidation;
import br.com.events.band.infrastructure.process.sheetMusic.delete.DeleteSheetMusicValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteSheetMusicValidatorImpl implements DeleteSheetMusicValidator {

    private final List<DeleteSheetMusicValidation> validations;

    @Override
    public void callProcesses(DeleteSheetMusicUseCaseForm param) {
        validations.forEach(v -> v.validate(param));
    }
}
