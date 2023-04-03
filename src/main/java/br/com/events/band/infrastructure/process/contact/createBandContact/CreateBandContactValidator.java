package br.com.events.band.infrastructure.process.contact.createBandContact;

import br.com.events.band.domain.io.contact.createBandContact.useCase.in.CreateBandContactUseCaseForm;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcessCaller;

/**
 * This class dictates that is a {@link CreateBandContactUseCaseForm} is going to be validated
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateBandContactValidator extends BaseVoidReturnProcessCaller<CreateBandContactUseCaseForm> {
}
