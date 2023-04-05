package br.com.events.band.application.controller.v1;

import br.com.events.band.domain.io.musician.create.rest.in.CreateMusicianRestForm;
import br.com.events.band.domain.mapper.musician.CreateMusicianMapper;
import br.com.events.band.infrastructure.controller.v1.MusicianControllerV1Doc;
import br.com.events.band.infrastructure.useCase.musician.CreateMusicianUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/musician")
@RequiredArgsConstructor
public class MusicianControllerV1 implements MusicianControllerV1Doc {

    private final CreateMusicianUseCase createMusicianUseCase;

    @Override
    @PostMapping("/band/{bandUuid}")
    public ResponseEntity<Void> createMusician(
            @PathVariable("bandUuid") String bandUuid,
            @RequestBody @Valid CreateMusicianRestForm form
    ) {
        var mappedForm = CreateMusicianMapper.from(form, bandUuid);

        createMusicianUseCase.execute(mappedForm);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
