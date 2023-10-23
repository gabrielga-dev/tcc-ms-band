package br.com.events.band.application.process.musician;

import br.com.events.band.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.infrastructure.process.musician.MusicianMethodValidation;
import br.com.events.band.infrastructure.process.musician.MusicianMethodValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MusicianMethodValidatorImpl implements MusicianMethodValidator {

    private final List<MusicianMethodValidation> validations;

    @Override
    public void callProcesses(MusicianValidationDto dto) {
        validations.stream()
                .filter(validation -> validation.matches(dto))
                .forEach(validations -> validations.validate(dto));

    }
}
