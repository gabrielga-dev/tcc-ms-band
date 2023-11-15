package br.com.events.band.older.application.process.music.create;

import br.com.events.band.older.domain.io.music.create.in.CreateMusicUseCaseForm;
import br.com.events.band.older.infrastructure.process.music.create.CreateMusicValidation;
import br.com.events.band.older.infrastructure.process.music.create.CreateMusicValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateMusicValidatorImpl implements CreateMusicValidator {

    private final List<CreateMusicValidation> validations;

    @Override
    public void callProcesses(CreateMusicUseCaseForm param) {
        validations.forEach(v -> v.validate(param));
    }
}
