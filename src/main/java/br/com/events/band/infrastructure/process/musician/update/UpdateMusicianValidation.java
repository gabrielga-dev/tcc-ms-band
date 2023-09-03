package br.com.events.band.infrastructure.process.musician.update;

import br.com.events.band.domain.io.musician.update.useCase.in.UpdateMusicianUseCaseForm;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcess;

/**
 * This interface extends the {@link BaseVoidReturnProcess} and dictates to it that the incoming data type will be
 * {@link UpdateMusicianUseCaseForm}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface UpdateMusicianValidation extends BaseVoidReturnProcess<UpdateMusicianUseCaseForm> {
}
