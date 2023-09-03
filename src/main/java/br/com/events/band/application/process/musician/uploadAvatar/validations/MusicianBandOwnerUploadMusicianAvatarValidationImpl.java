package br.com.events.band.application.process.musician.uploadAvatar.validations;

import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.application.process.exception.MusicianDoesNotExistException;
import br.com.events.band.domain.io.musician.uploadAvatar.in.UploadMusicianAvatarRequest;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.uploadAvatar.UploadMusicianAvatarValidation;
import br.com.events.band.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MusicianBandOwnerUploadMusicianAvatarValidationImpl implements UploadMusicianAvatarValidation {

    private final MusicianRepository musicianRepository;

    @Override
    public void validate(UploadMusicianAvatarRequest toValidate) {
        var musician = musicianRepository.findById(toValidate.getMusicianUuid())
                .orElseThrow(MusicianDoesNotExistException::new);
        var bandOwnerUuid = musician.getBand().getOwnerUuid();
        var userUuid = AuthUtil.getAuthenticatedPerson().getUuid();

        if(!musician.getActive() || !Objects.equals(bandOwnerUuid, userUuid)){
            throw new BandOwnerException();
        }
    }
}
