package br.com.events.band.application.process.musician.delete;

import br.com.events.band.domain.io.musician.delete.useCase.in.DeleteMusicianUseCaseForm;
import br.com.events.band.infrastructure.process.musician.delete.DeleteMusicianValidation;
import br.com.events.band.infrastructure.process.musician.delete.DeleteMusicianValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteMusicianValidatorImpl implements DeleteMusicianValidator {

    private final List<DeleteMusicianValidation> validations;

    @Override
    public void callProcesses(DeleteMusicianUseCaseForm param) {
        validations.forEach(v -> v.validate(param));
    }
}
