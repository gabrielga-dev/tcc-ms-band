package br.com.events.band.infrastructure.process.music.update;

import br.com.events.band.domain.io.music.create.in.CreateMusicUseCaseForm;
import br.com.events.band.domain.io.music.update.in.UpdateMusicUseCaseForm;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcess;

public interface UpdateMusicValidation extends BaseVoidReturnProcess<UpdateMusicUseCaseForm> {
}
