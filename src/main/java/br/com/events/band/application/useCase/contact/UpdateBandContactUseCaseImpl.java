package br.com.events.band.application.useCase.contact;

import br.com.events.band.application.process.contact.exception.BandContactNonExistenceException;
import br.com.events.band.domain.io.contact.updateBandContact.useCase.in.UpdateBandContactUseCaseForm;
import br.com.events.band.domain.mapper.contact.UpdateBandContactMapper;
import br.com.events.band.domain.repository.ContactRepository;
import br.com.events.band.infrastructure.process.contact.operate.OperateBandContactValidator;
import br.com.events.band.infrastructure.useCase.contact.UpdateBandContactUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements the update process of a band's contact
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class UpdateBandContactUseCaseImpl implements UpdateBandContactUseCase {

    private final ContactRepository contactRepository;
    private final OperateBandContactValidator operateBandContactValidation;

    @Override
    public Void execute(UpdateBandContactUseCaseForm param) {
        var dtoToValidate = UpdateBandContactMapper.toDtoToValidate(param);
        operateBandContactValidation.callProcesses(dtoToValidate);

        var contact = contactRepository.findById(param.getContactUuid())
                .orElseThrow(BandContactNonExistenceException::new);

        UpdateBandContactMapper.transferData(contact, param);

        contactRepository.save(contact);
        return null;
    }
}
