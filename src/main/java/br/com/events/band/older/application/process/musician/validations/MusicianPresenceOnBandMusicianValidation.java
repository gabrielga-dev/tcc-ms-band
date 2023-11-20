package br.com.events.band.older.application.process.musician.validations;

import br.com.events.band.older.application.process.musician.exception.MusicianNotPresentOnBandException;
import br.com.events.band.older.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.newer.adapter.repository.jpa.MusicianJpaRepository;
import br.com.events.band.older.infrastructure.process.musician.MusicianMethodValidation;
import br.com.events.band.older.domain.type.MethodValidationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MusicianPresenceOnBandMusicianValidation implements MusicianMethodValidation {

    private final MusicianJpaRepository musicianRepository;

    @Override
    public boolean matches(MusicianValidationDto dto) {
        return List.of(MethodValidationType.DELETE, MethodValidationType.EDIT).contains(dto.getMethodType());
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
