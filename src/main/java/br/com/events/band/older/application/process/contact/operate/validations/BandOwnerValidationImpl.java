package br.com.events.band.older.application.process.contact.operate.validations;

import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.older.domain.io.process.contact.operate.OperateBandContactDTO;
import br.com.events.band.older.domain.repository.BandRepository;
import br.com.events.band.older.infrastructure.process.contact.operate.OperateBandContactValidation;
import br.com.events.band.older.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandOwnerValidationImpl implements OperateBandContactValidation {

    private final BandRepository bandRepository;

    @Override
    public void validate(OperateBandContactDTO toValidate) {
        var band = bandRepository.findById(toValidate.getBandUuid())
                .orElseThrow(BandNonExistenceException::new);

        if (!band.getOwnerUuid().equals(AuthUtil.getAuthenticatedPerson().getUuid())){
            throw new BandOwnerException();
        }
    }
}
