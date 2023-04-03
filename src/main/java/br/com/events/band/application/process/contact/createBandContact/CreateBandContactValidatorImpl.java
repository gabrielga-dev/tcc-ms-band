package br.com.events.band.application.process.contact.createBandContact;

import br.com.events.band.domain.io.contact.createBandContact.useCase.in.CreateBandContactUseCaseForm;
import br.com.events.band.infrastructure.process.contact.createBandContact.CreateBandContactValidation;
import br.com.events.band.infrastructure.process.contact.createBandContact.CreateBandContactValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateBandContactValidatorImpl implements CreateBandContactValidator {

    private final List<CreateBandContactValidation> validations;

    @Override
    public void callProcesses(CreateBandContactUseCaseForm param) {
        validations.forEach(validation -> validation.validate(param));
    }
}
