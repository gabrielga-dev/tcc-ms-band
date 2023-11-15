package br.com.events.band.older.infrastructure.useCase.musicSheet;

import br.com.events.band.older.domain.io.musicSheet.delete.useCase.in.DeleteSheetMusicUseCaseForm;
import br.com.events.band.older.infrastructure.useCase.UseCaseBase;

public interface DeleteMusicSheetUseCase extends UseCaseBase<DeleteSheetMusicUseCaseForm, Void> {

    default void execute(String sheetUuid) {
        var mappedForm = DeleteSheetMusicUseCaseForm
                .builder()
                .sheetUuid(sheetUuid)
                .build();
        this.execute(mappedForm);
    }
}
