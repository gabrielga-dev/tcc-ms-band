package br.com.events.band.application.process.musician.uploadAvatar;

import br.com.events.band.domain.io.musician.uploadAvatar.in.UploadMusicianAvatarRequest;
import br.com.events.band.infrastructure.process.musician.uploadAvatar.UploadMusicianAvatarValidation;
import br.com.events.band.infrastructure.process.musician.uploadAvatar.UploadMusicianAvatarValidationCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UploadMusicianAvatarValidationCallerImpl implements UploadMusicianAvatarValidationCaller {

    private final List<UploadMusicianAvatarValidation> validations;

    @Override
    public void callProcesses(UploadMusicianAvatarRequest param) {
        validations.forEach(v -> v.validate(param));
    }
}
