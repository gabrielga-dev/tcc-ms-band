package br.com.events.band.older.application.process.band.update.validations;

import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.older.domain.io.process.band.update.UpdateBandProcessDTO;
import br.com.events.band.older.infrastructure.process.band.update.UpdateBandValidation;
import br.com.events.band.older.util.AuthUtil;
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
