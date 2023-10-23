package br.com.events.band.application.process.band;

import br.com.events.band.domain.io._new.band.dto.BandValidationDto;
import br.com.events.band.infrastructure.process.band.BandMethodValidation;
import br.com.events.band.infrastructure.process.band.BandMethodValidator;
import br.com.events.band.infrastructure.process.band.create.CreateBandValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class implements then{@link BandValidationDto} interface to call every {@link CreateBandValidation} to
 * validate the incoming band that will be inserted into the database
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class BandMethodValidatorImpl implements BandMethodValidator {

    private final List<BandMethodValidation> validations;

    @Override
    public void callProcesses(BandValidationDto dto) {
        validations.stream()
                .filter(validation -> validation.matches(dto))
                .forEach(validations -> validations.validate(dto));
    }
}
