package br.com.events.band.application.process.musician.delete.validation;

import br.com.events.band.application.process.musician.exception.MusicianNotPresentOnBandException;
import br.com.events.band.domain.io.musician.delete.in.DeleteMusicianUseCaseForm;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.delete.DeleteMusicianValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MusicianPresenceOnBandMusicianValidation implements DeleteMusicianValidation {

    private final MusicianRepository musicianRepository;

    @Override
    public void validate(DeleteMusicianUseCaseForm toValidate) {
        var presence = musicianRepository.existsByUuidAndBandUuid(
                toValidate.getMusicianUuid(), toValidate.getBandUuid()
        );

        if (!presence){
            throw new MusicianNotPresentOnBandException();
        }
    }
}
