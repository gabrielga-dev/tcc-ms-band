package br.com.events.band.infrastructure.process.musician.delete;

import br.com.events.band.domain.io.musician.delete.in.DeleteMusicianUseCaseForm;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcessCaller;

/**
 * This class dictates that is a {@link DeleteMusicianUseCaseForm} is going to be validated
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface DeleteMusicianValidator extends BaseVoidReturnProcessCaller<DeleteMusicianUseCaseForm> {
}
