package br.com.events.band.application.useCase.contact;

import br.com.events.band.domain.io.contact.deleteBandContact.useCase.in.DeleteBandContactUseCaseForm;
import br.com.events.band.domain.mapper.contact.DeleteBandContactMapper;
import br.com.events.band.domain.repository.ContactRepository;
import br.com.events.band.infrastructure.process.contact.operate.OperateBandContactValidator;
import br.com.events.band.infrastructure.useCase.contact.DeleteBandContactUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements the delete band's contact feature
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class DeleteBandContactUseCaseImpl implements DeleteBandContactUseCase {

    private final ContactRepository contactRepository;
    private final OperateBandContactValidator operateBandContactValidator;

    @Override
    public Void execute(DeleteBandContactUseCaseForm param) {
        var dtoToValidate = DeleteBandContactMapper.from(param);
        operateBandContactValidator.callProcesses(dtoToValidate);

        contactRepository.deleteById(param.getContactUuid());
        return null;
    }
}
