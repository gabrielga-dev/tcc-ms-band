package br.com.events.band.older.application.useCase.musicSheet;

import br.com.events.band.newer.core.exception.music.MusicNonExistenceException;
import br.com.events.band.older.domain.entity.Music;
import br.com.events.band.older.domain.entity.SheetMusic;
import br.com.events.band.newer.data.io.file.FileType;
import br.com.events.band.newer.data.io.file.FileDTO;
import br.com.events.band.older.domain.io.musicSheet.create.rest.out.CreateMusicSheetResult;
import br.com.events.band.older.domain.io.musicSheet.create.useCase.in.CreateSheetMusicUseCaseForm;
import br.com.events.band.older.domain.repository.MusicRepository;
import br.com.events.band.newer.data.io.file.FileOriginType;
import br.com.events.band.older.infrastructure.feign.msFile.FileFeignClient;
import br.com.events.band.older.infrastructure.process.sheetMusic.create.CreateSheetMusicValidator;
import br.com.events.band.older.infrastructure.useCase.musicSheet.CreateMusicSheetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class CreateMusicSheetUseCaseImpl implements CreateMusicSheetUseCase {

    private final CreateSheetMusicValidator createSheetMusicValidator;
    private final MusicRepository musicRepository;
    private final FileFeignClient fileFeignClient;

    @Override
    public CreateMusicSheetResult execute(CreateSheetMusicUseCaseForm param) {
        createSheetMusicValidator.callProcesses(param.getMusicUuid());

        var result = fileFeignClient.uploadFile(
                FileOriginType.MUSIC.name(),
                param.getMusicUuid(),
                FileType.SHEET_MUSIC,
                param.getFile()
        );


        var music = musicRepository.findById(param.getMusicUuid())
                .orElseThrow(MusicNonExistenceException::new);
        var newSheet = this.generateSheetEntity(music, param, result);
        music.getSheets().add(newSheet);

        var savedSheet = musicRepository.save(music);

        return new CreateMusicSheetResult(savedSheet.getUuid());
    }

    private SheetMusic generateSheetEntity(
            Music music, CreateSheetMusicUseCaseForm param, FileDTO result
    ) {
        var sheetMusic = new SheetMusic();

        sheetMusic.setMusic(music);
        sheetMusic.setObservation(param.getObservation());
        sheetMusic.setFileUuid(result.getUuid());

        return sheetMusic;
    }
}
