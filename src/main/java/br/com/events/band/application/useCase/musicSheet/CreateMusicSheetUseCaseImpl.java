package br.com.events.band.application.useCase.musicSheet;

import br.com.events.band.application.process.exception.MusicNonExistenceException;
import br.com.events.band.domain.entity.Music;
import br.com.events.band.domain.entity.SheetMusic;
import br.com.events.band.domain.io.feign.msFile.uploadFile.in.FileTypeFileClient;
import br.com.events.band.domain.io.feign.msFile.uploadFile.out.UploadFileFileClientResult;
import br.com.events.band.domain.io.musicSheet.create.rest.out.CreateMusicSheetResult;
import br.com.events.band.domain.io.musicSheet.create.useCase.in.CreateSheetMusicUseCaseForm;
import br.com.events.band.domain.repository.MusicRepository;
import br.com.events.band.domain.type.FileOriginType;
import br.com.events.band.infrastructure.feign.msFile.FileFeignClient;
import br.com.events.band.infrastructure.process.sheetMusic.CreateSheetMusicValidator;
import br.com.events.band.infrastructure.useCase.musicSheet.CreateMusicSheetUseCase;
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
                FileTypeFileClient.SHEET_MUSIC,
                param.getFile()
        ).getBody();


        var music = musicRepository.findById(param.getMusicUuid())
                .orElseThrow(MusicNonExistenceException::new);
        var newSheet = this.generateSheetEntity(music, param, result);
        music.getSheets().add(newSheet);

        musicRepository.save(music);

        return new CreateMusicSheetResult(result.getUuid());
    }

    private SheetMusic generateSheetEntity(
            Music music, CreateSheetMusicUseCaseForm param, UploadFileFileClientResult result
    ) {
        var sheetMusic = new SheetMusic();

        sheetMusic.setMusic(music);
        sheetMusic.setObservation(param.getObservation());
        sheetMusic.setFileUuid(result.getUuid());

        return sheetMusic;
    }
}
