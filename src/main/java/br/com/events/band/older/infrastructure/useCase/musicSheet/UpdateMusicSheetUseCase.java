package br.com.events.band.older.infrastructure.useCase.musicSheet;

import br.com.events.band.older.domain.io.musicSheet.create.rest.out.CreateMusicSheetResult;
import br.com.events.band.older.domain.io.musicSheet.update.useCase.in.UpdateSheetMusicUseCaseForm;
import br.com.events.band.older.infrastructure.useCase.UseCaseBase;

public interface UpdateMusicSheetUseCase extends UseCaseBase<UpdateSheetMusicUseCaseForm, CreateMusicSheetResult> {

    default CreateMusicSheetResult execute(String sheetUuid, String observation) {
        var mappedForm = UpdateSheetMusicUseCaseForm
                .builder()
                .sheetUuid(sheetUuid)
                .observation(observation)
                .build();
        return this.execute(mappedForm);
    }
}
