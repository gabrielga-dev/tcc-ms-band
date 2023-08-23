package br.com.events.band.infrastructure.useCase.musicSheet;

import br.com.events.band.domain.io.musicSheet.create.rest.out.CreateMusicSheetResult;
import br.com.events.band.domain.io.musicSheet.create.useCase.in.CreateSheetMusicUseCaseForm;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

public interface CreateMusicSheetUseCase extends UseCaseBase<CreateSheetMusicUseCaseForm, CreateMusicSheetResult> {
}
