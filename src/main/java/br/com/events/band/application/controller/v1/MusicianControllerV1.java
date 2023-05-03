package br.com.events.band.application.controller.v1;

import br.com.events.band.domain.io.musician.create.rest.in.CreateMusicianRestForm;
import br.com.events.band.domain.io.musician.list.rest.out.ListMusiciansRestResult;
import br.com.events.band.domain.io.musician.update.rest.in.UpdateMusicianRestForm;
import br.com.events.band.domain.io.musician.uploadAvatar.in.UploadMusicianAvatarRequest;
import br.com.events.band.domain.io.musician.uploadAvatar.out.UploadMusicianAvatarResult;
import br.com.events.band.domain.mapper.musician.CreateMusicianMapper;
import br.com.events.band.domain.mapper.musician.DeleteMusicianMapper;
import br.com.events.band.domain.mapper.musician.ListMusicianMapper;
import br.com.events.band.domain.mapper.musician.UpdateMusicianMapper;
import br.com.events.band.infrastructure.controller.v1.MusicianControllerV1Doc;
import br.com.events.band.infrastructure.useCase.musician.CreateMusicianUseCase;
import br.com.events.band.infrastructure.useCase.musician.DeleteMusiciansUseCase;
import br.com.events.band.infrastructure.useCase.musician.ListMusiciansUseCase;
import br.com.events.band.infrastructure.useCase.musician.UpdateMusicianUseCase;
import br.com.events.band.infrastructure.useCase.musician.UploadMusicianAvatarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/musician")
@RequiredArgsConstructor
public class MusicianControllerV1 implements MusicianControllerV1Doc {

    private final CreateMusicianUseCase createMusicianUseCase;
    private final ListMusiciansUseCase listMusiciansUseCase;
    private final DeleteMusiciansUseCase deleteMusiciansUseCase;
    private final UpdateMusicianUseCase updateMusicianUseCase;
    private final UploadMusicianAvatarUseCase uploadMusicianAvatarUseCase;

    @Override
    @PostMapping("/band/{bandUuid}")
    public ResponseEntity<Void> create(
            @PathVariable("bandUuid") String bandUuid,
            @RequestBody @Valid CreateMusicianRestForm form
    ) {
        var mappedForm = CreateMusicianMapper.from(form, bandUuid);

        createMusicianUseCase.execute(mappedForm);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping("/band/{bandUuid}")
    public ResponseEntity<List<ListMusiciansRestResult>> list(@PathVariable("bandUuid") String bandUuid) {
        var result = listMusiciansUseCase.execute(bandUuid);

        var mappedResult = ListMusicianMapper.fromUseCaseResult(result);

        return ResponseEntity.ok(mappedResult);
    }

    @Override
    @DeleteMapping("/band/{bandUuid}/{musicianUuid}")
    public ResponseEntity<Void> delete(
            @PathVariable("bandUuid") String bandUuid,
            @PathVariable("musicianUuid") String musicianUuid
    ) {
        var form = DeleteMusicianMapper.from(bandUuid,musicianUuid);
        deleteMusiciansUseCase.execute(form);
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping("band/{bandUuid}/{musicianUuid}")
    public ResponseEntity<Void> update(
            @PathVariable("bandUuid") String bandUuid,
            @PathVariable("musicianUuid") String musicianUuid,
            @RequestBody UpdateMusicianRestForm musicianRestForm
    ) {
        var mappedForm = UpdateMusicianMapper.from(musicianRestForm, bandUuid, musicianUuid);

        updateMusicianUseCase.execute(mappedForm);

        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{musicianUuid}/avatar")
    public ResponseEntity<UploadMusicianAvatarResult> uploadMusicianAvatar(
            @PathVariable("musicianUuid") String uuid,
            @RequestParam("avatar") MultipartFile file
    ) {
        var request = UploadMusicianAvatarRequest
                .builder()
                .musicianUuid(uuid)
                .file(file)
                .build();

        var result = uploadMusicianAvatarUseCase.execute(request);
        return ResponseEntity.ok(result);
    }
}
