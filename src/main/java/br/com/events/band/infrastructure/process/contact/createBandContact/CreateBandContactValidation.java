package br.com.events.band.infrastructure.process.contact.createBandContact;

import br.com.events.band.domain.io.contact.createBandContact.useCase.in.CreateBandContactUseCaseForm;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcess;

/**
 * This interface extends the {@link BaseVoidReturnProcess} and dictates to it that the incoming data type will be
 * {@link CreateBandContactUseCaseForm}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateBandContactValidation extends BaseVoidReturnProcess<CreateBandContactUseCaseForm> {

}
