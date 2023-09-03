package br.com.events.band.application.process.musician.uploadAvatar.validations;

import br.com.events.band.application.process.exception.MusicianDoesNotExistException;
import br.com.events.band.domain.io.musician.uploadAvatar.in.UploadMusicianAvatarRequest;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.uploadAvatar.UploadMusicianAvatarValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MusicianExistsUploadMusicianAvatarValidationImpl implements UploadMusicianAvatarValidation {

    private final MusicianRepository musicianRepository;

    @Override
    public void validate(UploadMusicianAvatarRequest toValidate) {
        musicianRepository.findById(toValidate.getMusicianUuid())
                .ifPresentOrElse(
                        m -> {
                            if (!m.getActive()) {
                                throw new MusicianDoesNotExistException();
                            }
                        },
                        () -> {
                            throw new MusicianDoesNotExistException();
                        }
                );
    }
}
