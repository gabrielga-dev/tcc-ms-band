package br.com.events.band.infrastructure.process.musician.create;

import br.com.events.band.domain.io.musician.create.useCase.in.CreateMusicianUseCaseForm;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcess;

/**
 * This interface extends the {@link BaseVoidReturnProcess} and dictates to it that the incoming data type will be
 * {@link CreateMusicianUseCaseForm}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateMusicianValidation extends BaseVoidReturnProcess<CreateMusicianUseCaseForm> {
}
