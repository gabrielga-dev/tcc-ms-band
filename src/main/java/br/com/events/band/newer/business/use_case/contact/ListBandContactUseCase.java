package br.com.events.band.newer.business.use_case.contact;

import br.com.events.band.newer.data.io.contact.response.ContactResponse;
import br.com.events.band.older.domain.io.contact.listBandContact.useCase.out.ListBandContactUseCaseResult;

import java.util.List;

/**
 * This interface dictates that will be returned a {@link List} of {@link ListBandContactUseCaseResult}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ListBandContactUseCase {

    List<ContactResponse> execute(String bandUuid);
}
