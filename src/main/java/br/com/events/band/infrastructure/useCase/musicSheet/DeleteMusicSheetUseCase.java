package br.com.events.band.infrastructure.useCase.musicSheet;

import br.com.events.band.domain.io.musicSheet.delete.useCase.in.DeleteSheetMusicUseCaseForm;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

public interface DeleteMusicSheetUseCase extends UseCaseBase<DeleteSheetMusicUseCaseForm, Void> {

    default void execute(String sheetUuid) {
        var mappedForm = DeleteSheetMusicUseCaseForm
                .builder()
                .sheetUuid(sheetUuid)
                .build();
        this.execute(mappedForm);
    }
}
