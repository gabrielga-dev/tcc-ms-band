package br.com.events.band.infrastructure.useCase.music;

import br.com.events.band.domain.io.music.create.out.CreateMusicResult;
import br.com.events.band.domain.io.music.update.in.UpdateMusicUseCaseForm;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

public interface UpdateMusicUseCase extends UseCaseBase<UpdateMusicUseCaseForm, CreateMusicResult> {

}
