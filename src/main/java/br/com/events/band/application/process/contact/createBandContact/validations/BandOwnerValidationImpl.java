package br.com.events.band.application.process.contact.createBandContact.validations;

import br.com.events.band.application.process.contact.exception.BandNonExistenceException;
import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.domain.io.contact.createBandContact.useCase.in.CreateBandContactUseCaseForm;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.infrastructure.process.contact.createBandContact.CreateBandContactValidation;
import br.com.events.band.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandOwnerValidationImpl implements CreateBandContactValidation {

    private final BandRepository bandRepository;

    @Override
    public void validate(CreateBandContactUseCaseForm toValidate) {
        var band = bandRepository.findById(toValidate.getBandUuid())
                .orElseThrow(BandNonExistenceException::new);

        if (!band.getOwnerUuid().equals(AuthUtil.getAuthenticatedPerson().getUuid())){
            throw new BandOwnerException();
        }
    }
}
