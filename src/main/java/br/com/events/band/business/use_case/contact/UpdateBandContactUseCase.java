package br.com.events.band.business.use_case.contact;

import br.com.events.band.data.io.contact.request.ContactRequest;

/**
 * This interface dictates what is needed to update a band's contact
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface UpdateBandContactUseCase {

    void execute(String bandUuid, String contactUuid, ContactRequest request);
}
