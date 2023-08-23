package br.com.events.band.infrastructure.useCase.music;

import br.com.events.band.domain.io.music.create.in.CreateMusicUseCaseForm;
import br.com.events.band.domain.io.music.create.out.CreateMusicResult;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

public interface CreateMusicUseCase extends UseCaseBase<CreateMusicUseCaseForm, CreateMusicResult> {
}
