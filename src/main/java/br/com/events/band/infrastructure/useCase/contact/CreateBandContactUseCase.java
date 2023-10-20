package br.com.events.band.infrastructure.useCase.contact;

import br.com.events.band.domain.io.contact.createBandContact.useCase.in.CreateBandContactUseCaseForm;
import br.com.events.band.domain.io.contact.listBandContact.rest.out.ListBandContactRestResult;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

/**
 * This interface dictates that is needed a {@link CreateBandContactUseCaseForm} object to create
 * a new band contact
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateBandContactUseCase extends UseCaseBase<CreateBandContactUseCaseForm, ListBandContactRestResult> {
}
