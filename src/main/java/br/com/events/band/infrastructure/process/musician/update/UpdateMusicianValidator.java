package br.com.events.band.infrastructure.process.musician.update;

import br.com.events.band.domain.io.musician.update.useCase.in.UpdateMusicianUseCaseForm;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcessCaller;

/**
 * This class dictates that is a {@link UpdateMusicianUseCaseForm} is going to be validated
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface UpdateMusicianValidator extends BaseVoidReturnProcessCaller<UpdateMusicianUseCaseForm> {
}
