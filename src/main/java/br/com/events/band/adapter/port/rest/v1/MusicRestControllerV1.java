package br.com.events.band.adapter.port.rest.v1;

import br.com.events.band.adapter.port.MusicPort;
import br.com.events.band.business.use_case.music.ActivateMusicUseCase;
import br.com.events.band.business.use_case.music.FindBandMusicsUseCase;
import br.com.events.band.business.use_case.music.FindMusicByCriteriaUseCase;
import br.com.events.band.business.use_case.music.FindMusicByUuidUseCase;
import br.com.events.band.data.io.commom.UuidHolderDTO;
import br.com.events.band.business.use_case.music.ContributeMusicUseCase;
import br.com.events.band.business.use_case.music.DeactivateMusicUseCase;
import br.com.events.band.business.use_case.music.UpdateMusicUseCase;
import br.com.events.band.data.io.music.criteria.MusicCriteria;
import br.com.events.band.data.io.music.request.MusicRequest;
import br.com.events.band.data.io.music.response.MusicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/music")
@RequiredArgsConstructor
public class MusicRestControllerV1 implements MusicPort {

    private final ContributeMusicUseCase contributeMusicUseCase;
    private final UpdateMusicUseCase updateMusicUseCase;
    private final DeactivateMusicUseCase deactivateMusicUseCase;
    private final ActivateMusicUseCase activateMusicUseCase;
    private final FindMusicByCriteriaUseCase findMusicByCriteriaUseCase;
    private final FindBandMusicsUseCase findBandMusicsUseCase;
    private final FindMusicByUuidUseCase findMusicByUuidUseCase;

    @Override
    @PostMapping("/band/{bandUuid}")
    @PreAuthorize("hasAuthority('BAND')")
    public ResponseEntity<UuidHolderDTO> contribute(
            @PathVariable("bandUuid") String bandUuid,
            @RequestBody @Valid MusicRequest request
    ) {
        var result = contributeMusicUseCase.execute(bandUuid, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    @PutMapping("/{musicUuid}")
    @PreAuthorize("hasAuthority('BAND')")
    public ResponseEntity<UuidHolderDTO> update(
            @PathVariable String musicUuid, @RequestBody @Valid MusicRequest music
    ) {
        var result = updateMusicUseCase.execute(musicUuid, music);
        return ResponseEntity.ok(result);
    }

    @Override
    @DeleteMapping("/{musicUuid}")
    @PreAuthorize("hasAuthority('BAND')")
    public ResponseEntity<Void> deactivate(@PathVariable String musicUuid) {
        deactivateMusicUseCase.execute(musicUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping
    @PreAuthorize("hasAuthority('BAND')")
    public ResponseEntity<Void> activate(@RequestParam("musicUuid") String musicUuid) {
        activateMusicUseCase.execute(musicUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAnyAuthority('BAND', 'CONTRACTOR')")
    public ResponseEntity<Page<MusicResponse>> findByCriteria(MusicCriteria criteria, Pageable pageable) {
        var page = findMusicByCriteriaUseCase.execute(criteria, pageable);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping("/band/{bandUuid}")
    @PreAuthorize("hasAnyAuthority('BAND', 'CONTRACTOR')")
    public ResponseEntity<Page<MusicResponse>> findBandMusics(
            @PathVariable("bandUuid") String bandUuid, MusicCriteria criteria, Pageable pageable
    ) {
        var musics = findBandMusicsUseCase.execute(bandUuid, criteria, pageable);
        return ResponseEntity.ok(musics);
    }

    @Override
    @GetMapping("/{musicUuid}")
    public ResponseEntity<MusicResponse> findByUuid(@PathVariable("musicUuid") String musicUuid) {
        var music = findMusicByUuidUseCase.execute(musicUuid);
        return ResponseEntity.ok(music);
    }
}
