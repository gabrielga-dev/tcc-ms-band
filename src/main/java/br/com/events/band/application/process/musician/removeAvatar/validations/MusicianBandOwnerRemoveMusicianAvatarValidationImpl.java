package br.com.events.band.application.process.musician.removeAvatar.validations;

import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.application.process.exception.MusicianDoesNotExistException;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.removeAvatar.RemoveMusicianAvatarValidation;
import br.com.events.band.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MusicianBandOwnerRemoveMusicianAvatarValidationImpl implements RemoveMusicianAvatarValidation {

    private final MusicianRepository musicianRepository;

    @Override
    public void validate(String toValidate) {
        var musician = musicianRepository.findById(toValidate)
                .orElseThrow(MusicianDoesNotExistException::new);
        var bandOwnerUuid = musician.getBand().getOwnerUuid();
        var userUuid = AuthUtil.getAuthenticatedPerson().getUuid();

        if(!musician.getActive() || !Objects.equals(bandOwnerUuid, userUuid)){
            throw new BandOwnerException();
        }
    }
}
