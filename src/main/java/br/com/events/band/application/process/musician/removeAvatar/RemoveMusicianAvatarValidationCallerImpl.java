package br.com.events.band.application.process.musician.removeAvatar;

import br.com.events.band.infrastructure.process.musician.removeAvatar.RemoveMusicianAvatarValidation;
import br.com.events.band.infrastructure.process.musician.removeAvatar.RemoveMusicianAvatarValidationCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RemoveMusicianAvatarValidationCallerImpl implements RemoveMusicianAvatarValidationCaller {

    private final List<RemoveMusicianAvatarValidation> validations;

    @Override
    public void callProcesses(String param) {
        validations.forEach(v -> v.validate(param));
    }
}
