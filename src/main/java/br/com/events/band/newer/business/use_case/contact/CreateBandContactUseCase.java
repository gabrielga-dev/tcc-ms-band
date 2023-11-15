package br.com.events.band.newer.business.use_case.contact;

import br.com.events.band.newer.data.io.contact.request.ContactRequest;
import br.com.events.band.newer.data.io.contact.response.ContactResponse;
import br.com.events.band.older.domain.io.contact.createBandContact.useCase.in.CreateBandContactUseCaseForm;

/**
 * This interface dictates that is needed a {@link CreateBandContactUseCaseForm} object to create
 * a new band contact
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateBandContactUseCase {

    ContactResponse execute(String bandUuid, ContactRequest request);
}
