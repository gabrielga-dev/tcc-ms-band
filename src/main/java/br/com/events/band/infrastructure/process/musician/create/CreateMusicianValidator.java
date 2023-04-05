package br.com.events.band.infrastructure.process.musician.create;

import br.com.events.band.domain.io.musician.create.useCase.in.CreateMusicianUseCaseForm;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcessCaller;

/**
 * This class dictates that is a {@link CreateMusicianUseCaseForm} is going to be validated
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateMusicianValidator extends BaseVoidReturnProcessCaller<CreateMusicianUseCaseForm> {
}
