package br.com.events.band.older.application.useCase.contact;

import br.com.events.band.older.domain.io.contact.deleteBandContact.useCase.in.DeleteBandContactUseCaseForm;
import br.com.events.band.older.domain.mapper.contact.DeleteBandContactMapper;
import br.com.events.band.older.domain.repository.ContactRepository;
import br.com.events.band.older.infrastructure.process.contact.operate.OperateBandContactValidator;
import br.com.events.band.older.infrastructure.useCase.contact.DeleteBandContactUseCase;
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
