package br.com.events.band.adapter.port.rest.v1;

import br.com.events.band.adapter.port.MusicPort;
import br.com.events.band.data.io.commom.UuidHolderDTO;
import br.com.events.band.business.use_case.music.ContributeMusicUseCase;
import br.com.events.band.business.use_case.music.DeleteMusicUseCase;
import br.com.events.band.business.use_case.music.UpdateMusicUseCase;
import br.com.events.band.data.io.music.request.MusicRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/music")
@RequiredArgsConstructor
public class MusicRestControllerV1 implements MusicPort {

    private final ContributeMusicUseCase contributeMusicUseCase;
    private final UpdateMusicUseCase updateMusicUseCase;
    private final DeleteMusicUseCase deleteMusicUseCase;

    @Override
    @PostMapping("/band/{bandUuid}")
    public ResponseEntity<UuidHolderDTO> contribute(
            @PathVariable("bandUuid") String bandUuid,
            @RequestBody @Valid MusicRequest request
    ) {
        var result = contributeMusicUseCase.execute(bandUuid, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    @PutMapping("/{musicUuid}/band/{bandUuid}")
    public ResponseEntity<UuidHolderDTO> update(
            @PathVariable String bandUuid, @PathVariable String musicUuid, @RequestBody @Valid MusicRequest music
    ) {
        var result = updateMusicUseCase.execute(bandUuid, musicUuid, music);
        return ResponseEntity.ok(result);
    }

    @Override
    @DeleteMapping("/{musicUuid}/band/{bandUuid}")
    public ResponseEntity<Void> deleteMusic(@PathVariable String bandUuid, @PathVariable String musicUuid) {
        deleteMusicUseCase.execute(bandUuid, musicUuid);
        return ResponseEntity.noContent().build();
    }
}
