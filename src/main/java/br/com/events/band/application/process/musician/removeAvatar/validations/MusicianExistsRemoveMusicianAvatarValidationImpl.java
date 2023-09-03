package br.com.events.band.application.process.musician.removeAvatar.validations;

import br.com.events.band.application.process.exception.MusicianDoesNotExistException;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.removeAvatar.RemoveMusicianAvatarValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MusicianExistsRemoveMusicianAvatarValidationImpl implements RemoveMusicianAvatarValidation {

    private final MusicianRepository musicianRepository;

    @Override
    public void validate(String toValidate) {
        musicianRepository.findById(toValidate)
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
