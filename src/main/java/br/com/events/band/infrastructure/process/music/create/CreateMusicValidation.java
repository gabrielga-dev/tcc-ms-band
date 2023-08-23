package br.com.events.band.infrastructure.process.music.create;

import br.com.events.band.domain.io.music.create.in.CreateMusicUseCaseForm;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcess;

public interface CreateMusicValidation extends BaseVoidReturnProcess<CreateMusicUseCaseForm> {
}
