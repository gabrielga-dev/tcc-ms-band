package br.com.events.band.infrastructure.useCase.contact;

import br.com.events.band.domain.io.contact.updateBandContact.useCase.in.UpdateBandContactUseCaseForm;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

/**
 * This interface dictates what is needed to update a band's contact
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface UpdateBandContactUseCase extends UseCaseBase<UpdateBandContactUseCaseForm, Void> {
}
