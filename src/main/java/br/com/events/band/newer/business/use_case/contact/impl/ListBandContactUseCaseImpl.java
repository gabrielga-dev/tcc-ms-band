package br.com.events.band.newer.business.use_case.contact.impl;

import br.com.events.band.newer.business.command.band.FindBandCommand;
import br.com.events.band.newer.business.use_case.contact.ListBandContactUseCase;
import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.data.io.contact.response.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the listing process of all contacts of a band
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class ListBandContactUseCaseImpl implements ListBandContactUseCase {

    private final FindBandCommand findBandCommand;

    @Override
    public List<ContactResponse> execute(String bandUuid) {
        var band = findBandCommand.byUuid(bandUuid).orElseThrow(BandNonExistenceException::new);
        if (!band.isActive()) {
            throw new BandNonExistenceException();
        }

        return band.getContacts().stream().map(ContactResponse::new).collect(Collectors.toList());
    }
}
