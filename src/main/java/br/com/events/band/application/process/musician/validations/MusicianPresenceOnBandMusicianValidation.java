package br.com.events.band.application.process.musician.validations;

import br.com.events.band.application.process.musician.exception.MusicianNotPresentOnBandException;
import br.com.events.band.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.MusicianMethodValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.events.band.domain.type.MethodValidationType.DELETE;
import static br.com.events.band.domain.type.MethodValidationType.EDIT;

@Component
@RequiredArgsConstructor
public class MusicianPresenceOnBandMusicianValidation implements MusicianMethodValidation {

    private final MusicianRepository musicianRepository;

    @Override
    public boolean matches(MusicianValidationDto dto) {
        return List.of(DELETE, EDIT).contains(dto.getMethodType());
    }

    @Override
    public void validate(MusicianValidationDto toValidate) {
        var presence = musicianRepository.existsByUuidAndBandUuid(
                toValidate.getMusicianUuid(), toValidate.getBandUuid()
        );

        if (!presence) {
            throw new MusicianNotPresentOnBandException();
        }
    }
}
