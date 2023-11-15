package br.com.events.band.older.application.process.contact.operate;

import br.com.events.band.older.domain.io.process.contact.operate.OperateBandContactDTO;
import br.com.events.band.older.infrastructure.process.contact.operate.OperateBandContactValidation;
import br.com.events.band.older.infrastructure.process.contact.operate.OperateBandContactValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OperateBandContactValidatorImpl implements OperateBandContactValidator {

    private final List<OperateBandContactValidation> validations;

    @Override
    public void callProcesses(OperateBandContactDTO param) {
        validations
                .stream().filter(validations -> validations.matches(param))
                .forEach(validation -> validation.validate(param));
    }
}
