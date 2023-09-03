package br.com.events.band.application.process.music.delete;

import br.com.events.band.infrastructure.process.music.delete.DeleteMusicValidation;
import br.com.events.band.infrastructure.process.music.delete.DeleteMusicValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteMusicValidatorImpl implements DeleteMusicValidator {

    private final List<DeleteMusicValidation> validations;

    @Override
    public void callProcesses(String musicUuid) {
        validations.forEach(v -> v.validate(musicUuid));
    }
}
