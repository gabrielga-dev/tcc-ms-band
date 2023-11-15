package br.com.events.band.older.infrastructure.process.band.create;

import br.com.events.band.older.domain.io.band.create.useCase.in.CreateBandUseCaseForm;
import br.com.events.band.older.infrastructure.process.BaseVoidReturnProcessCaller;

/**
 * This interface extends the {@link BaseVoidReturnProcessCaller} and dictates to it that the incoming data type will be
 * {@link CreateBandUseCaseForm}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateBandValidator extends BaseVoidReturnProcessCaller<CreateBandUseCaseForm> {

}
