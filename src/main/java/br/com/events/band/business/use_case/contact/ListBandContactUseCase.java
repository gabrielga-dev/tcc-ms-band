package br.com.events.band.business.use_case.contact;

import br.com.events.band.data.io.contact.response.ContactResponse;

import java.util.List;

/**
 * This interface dictates that will be returned a {@link List} of {@link ContactResponse}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ListBandContactUseCase {

    List<ContactResponse> execute(String bandUuid);
}
