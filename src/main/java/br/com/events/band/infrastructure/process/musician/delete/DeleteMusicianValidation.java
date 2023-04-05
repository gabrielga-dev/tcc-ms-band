package br.com.events.band.infrastructure.process.musician.delete;

import br.com.events.band.domain.io.musician.delete.useCase.in.DeleteMusicianUseCaseForm;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcess;

/**
 * This interface extends the {@link BaseVoidReturnProcess} and dictates to it that the incoming data type will be
 * {@link DeleteMusicianUseCaseForm}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface DeleteMusicianValidation extends BaseVoidReturnProcess<DeleteMusicianUseCaseForm> {
}
