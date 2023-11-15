package br.com.events.band.older.infrastructure.process.music.create;

import br.com.events.band.older.domain.io.music.create.in.CreateMusicUseCaseForm;
import br.com.events.band.older.infrastructure.process.BaseVoidReturnProcessCaller;

/**
 * This class dictates that is a {@link CreateMusicUseCaseForm} is going to be validated
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateMusicValidator extends BaseVoidReturnProcessCaller<CreateMusicUseCaseForm> {
}
