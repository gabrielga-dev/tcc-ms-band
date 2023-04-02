package br.com.events.band.application.process.band.update.validations;

import br.com.events.band.application.process.band.exception.BandOwnerException;
import br.com.events.band.domain.io.process.band.update.UpdateBandProcessDTO;
import br.com.events.band.infrastructure.process.band.update.UpdateBandValidation;
import br.com.events.band.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * This class validates if the incoming event's date is on present or future
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class BandOwnerUpdateBandValidationImpl implements UpdateBandValidation {

    @Override
    public void validate(final UpdateBandProcessDTO form) {
        var authenticatedPersonUuid = AuthUtil.getAuthenticatedPerson().getUuid();
        if(!Objects.equals(authenticatedPersonUuid, form.getEntity().getOwnerUuid())){
            throw new BandOwnerException();
        }
    }
}
