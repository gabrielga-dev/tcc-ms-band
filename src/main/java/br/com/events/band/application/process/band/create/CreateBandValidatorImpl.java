package br.com.events.band.application.process.band.create;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.events.band.domain.io.band.create.useCase.in.CreateBandUseCaseForm;
import br.com.events.band.infrastructure.process.band.create.CreateBandValidation;
import br.com.events.band.infrastructure.process.band.create.CreateBandValidator;
import lombok.RequiredArgsConstructor;

/**
 * This class implements then{@link CreateBandValidator} interface to call every {@link CreateBandValidation} to
 * validate the incoming band that will be inserted into the database
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreateBandValidatorImpl implements CreateBandValidator {

    private final List<CreateBandValidation> validations;

    @Override
    public void callProcesses(final CreateBandUseCaseForm param) {
        validations.forEach(
            validation -> validation.validate(param)
        );
    }
}
