package br.com.events.band.application.process.contact.operate.validations;

import br.com.events.band.application.process.exception.BandNonExistenceException;
import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.domain.io.process.contact.operate.OperateBandContactDTO;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.infrastructure.process.contact.operate.OperateBandContactValidation;
import br.com.events.band.util.AuthUtil;
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
