package br.com.events.band.application.process.band.update;

import br.com.events.band.domain.io.process.band.update.UpdateBandProcessDTO;
import br.com.events.band.infrastructure.process.band.update.UpdateBandValidation;
import br.com.events.band.infrastructure.process.band.update.UpdateBandValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class implements then{@link UpdateBandValidator} interface to call every {@link UpdateBandValidation} to
 * validate the incoming band data that will be inserted into the database
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class UpdateBandValidatorImpl implements UpdateBandValidator {

    private final List<UpdateBandValidation> validations;

    @Override
    public void callProcesses(final UpdateBandProcessDTO param) {
        validations.stream()
                .filter(
                        validations -> validations.matches(param)
                ).forEach(
                        validation -> validation.validate(param)
                );
    }
}
