package br.com.events.band.business.command.contact;

import br.com.events.band.adapter.repository.ContactRepository;
import br.com.events.band.data.model.table.band.contact.ContactTable;
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
