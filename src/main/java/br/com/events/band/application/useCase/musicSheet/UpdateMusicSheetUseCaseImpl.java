package br.com.events.band.application.useCase.musicSheet;

import br.com.events.band.application.process.exception.SheetMusicNonExistenceException;
import br.com.events.band.domain.io.musicSheet.create.rest.out.CreateMusicSheetResult;
import br.com.events.band.domain.io.musicSheet.update.useCase.in.UpdateSheetMusicUseCaseForm;
import br.com.events.band.domain.repository.SheetMusicRepository;
import br.com.events.band.infrastructure.process.sheetMusic.update.UpdateSheetMusicValidator;
import br.com.events.band.infrastructure.useCase.musicSheet.UpdateMusicSheetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateMusicSheetUseCaseImpl implements UpdateMusicSheetUseCase {

    private final UpdateSheetMusicValidator validator;
    private final SheetMusicRepository sheetMusicRepository;

    @Override
    public CreateMusicSheetResult execute(UpdateSheetMusicUseCaseForm param) {
        validator.callProcesses(param.getSheetUuid());

        var sheet = sheetMusicRepository.findById(param.getSheetUuid())
                .orElseThrow(SheetMusicNonExistenceException::new);

        sheet.setObservation(param.getObservation());

        sheetMusicRepository.save(sheet);

        return new CreateMusicSheetResult(sheet.getUuid());
    }
}
