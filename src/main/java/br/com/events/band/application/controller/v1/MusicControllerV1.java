package br.com.events.band.application.controller.v1;

import br.com.events.band.application.useCase.musicSheet.CreateMusicSheetUseCaseImpl;
import br.com.events.band.domain.io.music.create.CreateMusicMapper;
import br.com.events.band.domain.io.music.create.in.CreateMusicForm;
import br.com.events.band.domain.io.music.create.out.CreateMusicResult;
import br.com.events.band.domain.io.musicSheet.create.rest.out.CreateMusicSheetResult;
import br.com.events.band.infrastructure.controller.v1.MusicControllerV1Doc;
import br.com.events.band.infrastructure.useCase.music.CreateMusicUseCase;
import br.com.events.band.infrastructure.useCase.music.DeleteMusicUseCase;
import br.com.events.band.infrastructure.useCase.music.UpdateMusicUseCase;
import br.com.events.band.infrastructure.useCase.musicSheet.DeleteMusicSheetUseCase;
import br.com.events.band.infrastructure.useCase.musicSheet.UpdateMusicSheetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/v1/music")
@RequiredArgsConstructor
public class MusicControllerV1 implements MusicControllerV1Doc {

    private final CreateMusicUseCase createMusicUseCase;
    private final CreateMusicSheetUseCaseImpl createMusicSheetUseCase;
    private final UpdateMusicUseCase updateMusicUseCase;
    private final DeleteMusicUseCase deleteMusicUseCase;
    private final DeleteMusicSheetUseCase deleteMusicSheetUseCase;
    private final UpdateMusicSheetUseCase updateMusicSheetUseCase;

    @Override
    @PostMapping("/band/{bandUuid}")
    public ResponseEntity<CreateMusicResult> create(
            @PathVariable("bandUuid") String bandUuid,
            @RequestBody @Valid CreateMusicForm bandRestForm
    ) {
        var mappedForm = CreateMusicMapper.from(bandUuid, bandRestForm);
        var result = createMusicUseCase.execute(mappedForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    @PostMapping("/{musicUuid}/file")
    public ResponseEntity<CreateMusicSheetResult> createSheet(
            @PathVariable("musicUuid") String musicUuid,
            @RequestParam("observation")
            @Size(max = 1000, message = "O campo de observação deve ter, no máximo, 1000 caracteres.")
            String observation,
            @RequestPart("file") MultipartFile file
    ) {
        var result = createMusicSheetUseCase.execute(musicUuid, observation, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    @PutMapping("/{musicUuid}")
    public ResponseEntity<CreateMusicResult> update(
            @PathVariable String musicUuid, @RequestBody @Valid CreateMusicForm music
    ) {
        var result = updateMusicUseCase.execute(musicUuid, music);
        return ResponseEntity.ok(result);
    }

    @Override
    @PutMapping("/{sheetUuid}/sheet")
    public ResponseEntity<CreateMusicSheetResult> updateSheet(
            @PathVariable String sheetUuid, @RequestParam String observation
    ) {
        var response = updateMusicSheetUseCase.execute(sheetUuid, observation);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("/{musicUuid}")
    public ResponseEntity<Void> deleteMusic(@PathVariable String musicUuid) {
        deleteMusicUseCase.execute(musicUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{sheetUuid}/sheet")
    public ResponseEntity<Void> removeSheet(
            @PathVariable String sheetUuid
    ) {
        deleteMusicSheetUseCase.execute(sheetUuid);
        return ResponseEntity.noContent().build();
    }
}
