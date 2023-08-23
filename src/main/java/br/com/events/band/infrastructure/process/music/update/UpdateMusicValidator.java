package br.com.events.band.infrastructure.process.music.update;

import br.com.events.band.domain.io.music.create.in.CreateMusicUseCaseForm;
import br.com.events.band.domain.io.music.update.in.UpdateMusicUseCaseForm;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcessCaller;

/**
 * This class dictates that is a {@link CreateMusicUseCaseForm} is going to be validated
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface UpdateMusicValidator extends BaseVoidReturnProcessCaller<UpdateMusicUseCaseForm> {

    default void validate(UpdateMusicUseCaseForm form){
        this.callProcesses(form);
    }
}
