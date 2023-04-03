package br.com.events.band.application.useCase.contact;

import br.com.events.band.domain.io.contact.createBandContact.useCase.in.CreateBandContactUseCaseForm;
import br.com.events.band.domain.mapper.contact.CreateBandContactMapper;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.domain.repository.ContactRepository;
import br.com.events.band.infrastructure.process.contact.createBandContact.CreateBandContactValidator;
import br.com.events.band.infrastructure.useCase.contact.CreateBandContactUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateBandContactUseCaseImpl implements CreateBandContactUseCase {

    private final ContactRepository contactRepository;
    private final BandRepository bandRepository;
    private final CreateBandContactValidator createBandContactValidator;

    @Override
    public Void execute(CreateBandContactUseCaseForm param) {
        createBandContactValidator.callProcesses(param);

        var toSave = CreateBandContactMapper.from(param);
        toSave.setBand(bandRepository.findById(param.getBandUuid()).get());

        contactRepository.save(toSave);
        return null;
    }
}
