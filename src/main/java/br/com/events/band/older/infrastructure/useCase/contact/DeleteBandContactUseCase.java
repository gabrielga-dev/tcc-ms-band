package br.com.events.band.older.infrastructure.useCase.contact;

import br.com.events.band.older.domain.io.contact.deleteBandContact.useCase.in.DeleteBandContactUseCaseForm;
import br.com.events.band.older.infrastructure.useCase.UseCaseBase;

/**
 * This interface dictates what is needed to delete a band's contact
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface DeleteBandContactUseCase extends UseCaseBase<DeleteBandContactUseCaseForm, Void> {
}
