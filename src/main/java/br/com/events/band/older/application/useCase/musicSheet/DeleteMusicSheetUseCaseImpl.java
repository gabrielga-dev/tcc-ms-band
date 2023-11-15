package br.com.events.band.older.application.useCase.musicSheet;

import br.com.events.band.newer.core.exception.SheetMusicNonExistenceException;
import br.com.events.band.older.domain.io.musicSheet.delete.useCase.in.DeleteSheetMusicUseCaseForm;
import br.com.events.band.older.domain.repository.SheetMusicRepository;
import br.com.events.band.older.infrastructure.process.sheetMusic.delete.DeleteSheetMusicValidator;
import br.com.events.band.older.infrastructure.useCase.musicSheet.DeleteMusicSheetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteMusicSheetUseCaseImpl implements DeleteMusicSheetUseCase {

    private final DeleteSheetMusicValidator deleteSheetMusicValidator;
    private final SheetMusicRepository sheetMusicRepository;

    @Override
    public Void execute(DeleteSheetMusicUseCaseForm param) {
        deleteSheetMusicValidator.callProcesses(param);
        var sheetMusic = sheetMusicRepository.findById(param.getSheetUuid())
                .orElseThrow(SheetMusicNonExistenceException::new);

        sheetMusicRepository.delete(sheetMusic);
        return null;
    }
}
