package br.com.events.band.application.useCase.musicSheet;

import br.com.events.band.application.process.exception.SheetMusicNonExistenceException;
import br.com.events.band.domain.io.musicSheet.delete.useCase.in.DeleteSheetMusicUseCaseForm;
import br.com.events.band.domain.repository.SheetMusicRepository;
import br.com.events.band.infrastructure.process.sheetMusic.delete.DeleteSheetMusicValidator;
import br.com.events.band.infrastructure.useCase.musicSheet.DeleteMusicSheetUseCase;
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
