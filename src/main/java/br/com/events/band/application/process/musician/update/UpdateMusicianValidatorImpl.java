package br.com.events.band.application.process.musician.update;

import br.com.events.band.domain.io.musician.update.useCase.in.UpdateMusicianUseCaseForm;
import br.com.events.band.infrastructure.process.musician.update.UpdateMusicianValidation;
import br.com.events.band.infrastructure.process.musician.update.UpdateMusicianValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateMusicianValidatorImpl implements UpdateMusicianValidator {

    private final List<UpdateMusicianValidation> validations;

    @Override
    public void callProcesses(UpdateMusicianUseCaseForm param) {
        validations.forEach(
                v -> v.validate(param)
        );
    }
}
