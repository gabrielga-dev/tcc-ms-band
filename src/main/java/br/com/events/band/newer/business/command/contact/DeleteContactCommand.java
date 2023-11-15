package br.com.events.band.newer.business.command.contact;

import br.com.events.band.newer.adapter.reporitory.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteContactCommand {

    private final ContactRepository contactRepository;

    public void execute(String contactUuid) {
        contactRepository.deleteById(contactUuid);
    }
}
