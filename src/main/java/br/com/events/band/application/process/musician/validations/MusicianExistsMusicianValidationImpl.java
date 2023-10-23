package br.com.events.band.application.process.musician.validations;

import br.com.events.band.application.process.exception.MusicianDoesNotExistException;
import br.com.events.band.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.MusicianMethodValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.events.band.domain.type.MethodValidationType.DELETE;
import static br.com.events.band.domain.type.MethodValidationType.EDIT;
import static br.com.events.band.domain.type.MethodValidationType.REMOVE_FILE;
import static br.com.events.band.domain.type.MethodValidationType.UPLOAD_FILE;

@Component
@RequiredArgsConstructor
public class MusicianExistsMusicianValidationImpl implements MusicianMethodValidation {

    private final MusicianRepository musicianRepository;

    @Override
    public boolean matches(MusicianValidationDto dto) {
        return List.of(EDIT, DELETE, UPLOAD_FILE, REMOVE_FILE).contains(dto.getMethodType());
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
