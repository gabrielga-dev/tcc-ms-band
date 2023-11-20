package br.com.events.band.business.use_case.contact.impl;

import br.com.events.band.business.command.contact.SaveContactCommand;
import br.com.events.band.business.use_case.contact.CreateBandContactUseCase;
import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.data.io.contact.request.ContactRequest;
import br.com.events.band.data.model.table.band.contact.ContactTable;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.data.io.contact.response.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateBandContactUseCaseImpl implements CreateBandContactUseCase {

    private final FindBandCommand findBandCommand;
    private final SaveContactCommand saveContactCommand;

    @Override
    public ContactResponse execute(String bandUuid, ContactRequest request) {
        var band = findBandCommand.byUuidAndOwnerUuid(bandUuid, AuthUtil.getAuthenticatedPersonUuid())
                .orElseThrow(BandNonExistenceException::new);

        if (!band.isActive()) {
            throw new BandNonExistenceException();
        }

        var toSave = new ContactTable(request, band);
        toSave = saveContactCommand.execute(toSave);

        return new ContactResponse(toSave);
    }
}
