package br.com.events.band.business.use_case.contact;

import br.com.events.band.data.io.contact.request.ContactRequest;
import br.com.events.band.data.io.contact.response.ContactResponse;

/**
 * This interface dictates that is needed a {@link String} band uuid and {@link ContactRequest} object to create
 * a new band contact
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateBandContactUseCase {

    ContactResponse execute(String bandUuid, ContactRequest request);
}
