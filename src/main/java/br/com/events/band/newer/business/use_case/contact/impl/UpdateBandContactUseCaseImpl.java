package br.com.events.band.newer.business.use_case.contact.impl;

import br.com.events.band.newer.business.command.contact.FindContactCommand;
import br.com.events.band.newer.business.command.contact.SaveContactCommand;
import br.com.events.band.newer.business.use_case.contact.UpdateBandContactUseCase;
import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.newer.core.util.AuthUtil;
import br.com.events.band.newer.data.io.contact.request.ContactRequest;
import br.com.events.band.newer.core.exception.band.BandContactNonExistenceException;
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

    private final FindContactCommand findContactCommand;
    private final SaveContactCommand saveContactCommand;

    @Override
    public void execute(String bandUuid, String contactUuid, ContactRequest request) {
        var contact = findContactCommand.byUuidAndBandUuid(contactUuid, bandUuid)
                .orElseThrow(BandContactNonExistenceException::new);

        if (!contact.getBand().isActive()) {
            throw new BandNonExistenceException();
        }

        if (!AuthUtil.getAuthenticatedPersonUuid().equals(contact.getBand().getOwnerUuid())) {
            throw new BandOwnerException();
        }

        contact.update(request);

        saveContactCommand.execute(contact);
    }
}
