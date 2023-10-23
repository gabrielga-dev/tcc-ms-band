package br.com.events.band.application.process.musician.validations;

import br.com.events.band.application.process.exception.BandNonExistenceException;
import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.infrastructure.process.musician.MusicianMethodValidation;
import br.com.events.band.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.events.band.domain.type.MethodValidationType.CREATE;
import static br.com.events.band.domain.type.MethodValidationType.DELETE;
import static br.com.events.band.domain.type.MethodValidationType.EDIT;
import static br.com.events.band.domain.type.MethodValidationType.REMOVE_FILE;
import static br.com.events.band.domain.type.MethodValidationType.UPLOAD_FILE;

@Component
@RequiredArgsConstructor
public class BandOwnerMusicianValidation implements MusicianMethodValidation {

    private final BandRepository bandRepository;

    @Override
    public boolean matches(MusicianValidationDto dto) {
        return List.of(CREATE, DELETE, EDIT, UPLOAD_FILE, REMOVE_FILE).contains(dto.getMethodType());
    }

    @Override
    public void validate(MusicianValidationDto toValidate) {
        var band = bandRepository.findById(toValidate.getBandUuid())
                .orElseThrow(BandNonExistenceException::new);

        if (!band.getOwnerUuid().equals(AuthUtil.getAuthenticatedPerson().getUuid())) {
            throw new BandOwnerException();
        }
    }
}
