package br.com.events.band.older.application.process.musician.validations;

import br.com.events.band.newer.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.older.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.older.domain.repository.MusicianRepository;
import br.com.events.band.older.infrastructure.process.musician.MusicianMethodValidation;
import br.com.events.band.older.domain.type.MethodValidationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MusicianExistsMusicianValidationImpl implements MusicianMethodValidation {

    private final MusicianRepository musicianRepository;

    @Override
    public boolean matches(MusicianValidationDto dto) {
        return List.of(MethodValidationType.EDIT, MethodValidationType.DELETE, MethodValidationType.UPLOAD_FILE, MethodValidationType.REMOVE_FILE).contains(dto.getMethodType());
    }

    @Override
    public void validate(MusicianValidationDto toValidate) {
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
