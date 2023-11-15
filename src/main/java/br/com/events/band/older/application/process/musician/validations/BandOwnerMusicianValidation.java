package br.com.events.band.older.application.process.musician.validations;

import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.older.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.older.domain.repository.BandRepository;
import br.com.events.band.older.infrastructure.process.musician.MusicianMethodValidation;
import br.com.events.band.older.domain.type.MethodValidationType;
import br.com.events.band.older.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BandOwnerMusicianValidation implements MusicianMethodValidation {

    private final BandRepository bandRepository;

    @Override
    public boolean matches(MusicianValidationDto dto) {
        return List.of(MethodValidationType.CREATE, MethodValidationType.DELETE, MethodValidationType.EDIT, MethodValidationType.UPLOAD_FILE, MethodValidationType.REMOVE_FILE).contains(dto.getMethodType());
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
