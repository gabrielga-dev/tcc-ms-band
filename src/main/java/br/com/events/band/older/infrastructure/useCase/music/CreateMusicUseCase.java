package br.com.events.band.older.infrastructure.useCase.music;

import br.com.events.band.older.domain.io.music.create.in.CreateMusicUseCaseForm;
import br.com.events.band.older.domain.io.music.create.out.CreateMusicResult;
import br.com.events.band.older.infrastructure.useCase.UseCaseBase;

public interface CreateMusicUseCase extends UseCaseBase<CreateMusicUseCaseForm, CreateMusicResult> {
}
