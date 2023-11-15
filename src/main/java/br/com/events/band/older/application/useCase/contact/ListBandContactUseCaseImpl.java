package br.com.events.band.older.application.useCase.contact;

import br.com.events.band.older.domain.io.contact.listBandContact.useCase.out.ListBandContactUseCaseResult;
import br.com.events.band.older.domain.mapper.contact.ListBandContactMapper;
import br.com.events.band.older.domain.repository.ContactRepository;
import br.com.events.band.older.infrastructure.useCase.contact.ListBandContactUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class implements the listing process of all contacts of a band
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class ListBandContactUseCaseImpl implements ListBandContactUseCase {

    private final ContactRepository contactRepository;

    @Override
    public List<ListBandContactUseCaseResult> execute(String bandUuid) {
        var contacts = contactRepository.findByBandUuid(bandUuid);

        return ListBandContactMapper.from(contacts);
    }
}
