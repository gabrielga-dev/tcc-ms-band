package br.com.events.band.infrastructure.process.band.create;

import br.com.events.band.domain.io.band.create.useCase.in.CreateBandUseCaseForm;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcess;

/**
 * This interface extends the {@link BaseVoidReturnProcess} and dictates to it that the incoming data type will be
 * {@link CreateBandUseCaseForm}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateBandValidation extends BaseVoidReturnProcess<CreateBandUseCaseForm> {

}
