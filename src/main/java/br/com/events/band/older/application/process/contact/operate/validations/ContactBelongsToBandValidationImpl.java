package br.com.events.band.older.application.process.contact.operate.validations;

import br.com.events.band.older.application.process.contact.exception.BandContactNonExistenceException;
import br.com.events.band.older.application.process.contact.exception.ContactDoesNotBelongsToBandException;
import br.com.events.band.older.domain.io.process.contact.operate.OperateBandContactDTO;
import br.com.events.band.older.domain.repository.ContactRepository;
import br.com.events.band.older.infrastructure.process.contact.operate.OperateBandContactValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ContactBelongsToBandValidationImpl implements OperateBandContactValidation {

    private final ContactRepository contactRepository;

    @Override
    public boolean matches(OperateBandContactDTO param) {
        return Objects.nonNull(param.getContactUuid());
    }

    @Override
    public void validate(OperateBandContactDTO toValidate) {
        var contact = contactRepository.findById(toValidate.getContactUuid())
                .orElseThrow(BandContactNonExistenceException::new);

        if(!Objects.equals(contact.getBand().getUuid(), toValidate.getBandUuid())){
            throw new ContactDoesNotBelongsToBandException();
        }
    }
}
