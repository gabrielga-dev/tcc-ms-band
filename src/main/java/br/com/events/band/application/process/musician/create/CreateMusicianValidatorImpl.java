package br.com.events.band.application.process.musician.create;

import br.com.events.band.domain.io.musician.create.useCase.in.CreateMusicianUseCaseForm;
import br.com.events.band.infrastructure.process.musician.create.CreateMusicianValidation;
import br.com.events.band.infrastructure.process.musician.create.CreateMusicianValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateMusicianValidatorImpl implements CreateMusicianValidator {

    private final List<CreateMusicianValidation> validations;

    @Override
    public void callProcesses(CreateMusicianUseCaseForm param) {
        validations.forEach(
                v -> v.validate(param)
        );
    }
}
