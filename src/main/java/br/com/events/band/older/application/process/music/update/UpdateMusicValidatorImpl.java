package br.com.events.band.older.application.process.music.update;

import br.com.events.band.older.domain.io.music.update.in.UpdateMusicUseCaseForm;
import br.com.events.band.older.infrastructure.process.music.update.UpdateMusicValidation;
import br.com.events.band.older.infrastructure.process.music.update.UpdateMusicValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateMusicValidatorImpl implements UpdateMusicValidator {

    private final List<UpdateMusicValidation> validations;

    @Override
    public void callProcesses(UpdateMusicUseCaseForm param) {
        validations.forEach(v -> v.validate(param));
    }
}
