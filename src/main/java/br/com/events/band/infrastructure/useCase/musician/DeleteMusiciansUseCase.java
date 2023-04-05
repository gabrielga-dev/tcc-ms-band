package br.com.events.band.infrastructure.useCase.musician;

import br.com.events.band.domain.io.musician.delete.useCase.in.DeleteMusicianUseCaseForm;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

public interface DeleteMusiciansUseCase extends UseCaseBase<DeleteMusicianUseCaseForm, Void> {
}
