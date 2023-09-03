package br.com.events.band.infrastructure.useCase.musicSheet;

import br.com.events.band.domain.io.musicSheet.create.rest.out.CreateMusicSheetResult;
import br.com.events.band.domain.io.musicSheet.update.useCase.in.UpdateSheetMusicUseCaseForm;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

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
