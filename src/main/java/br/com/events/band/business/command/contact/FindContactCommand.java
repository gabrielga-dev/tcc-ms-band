package br.com.events.band.business.command.contact;

import br.com.events.band.adapter.repository.ContactRepository;
import br.com.events.band.data.model.table.band.contact.ContactTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindContactCommand {

    private final ContactRepository contactRepository;

    public Optional<ContactTable> byUuid(String uuid){
        return contactRepository.findById(uuid);
    }

    public Optional<ContactTable> byUuidAndBandUuid(String contactUuid, String bandUuid){
        return contactRepository.findByUuidAndBandUuid(contactUuid, bandUuid);
    }
}
