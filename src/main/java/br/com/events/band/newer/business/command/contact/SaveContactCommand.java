package br.com.events.band.newer.business.command.contact;

import br.com.events.band.newer.adapter.repository.ContactRepository;
import br.com.events.band.newer.data.model.table.ContactTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveContactCommand {

    private final ContactRepository contactRepository;

    public ContactTable execute(ContactTable contactTable) {
        return contactRepository.save(contactTable);
    }
}
