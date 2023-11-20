package br.com.events.band.adapter.port.rest.v1;

import br.com.events.band.adapter.port.MusicianPort;
import br.com.events.band.business.use_case.musician.CreateMusicianUseCase;
import br.com.events.band.business.use_case.musician.DeleteMusiciansUseCase;
import br.com.events.band.business.use_case.musician.FindMusicianByUuidUseCase;
import br.com.events.band.business.use_case.musician.FindMusiciansByCriteriaUseCase;
import br.com.events.band.business.use_case.musician.RemoveMusicianAvatarUseCase;
import br.com.events.band.business.use_case.musician.UpdateMusicianUseCase;
import br.com.events.band.data.io.commom.UuidHolderDTO;
import br.com.events.band.data.io.musician.request.MusicianRequest;
import br.com.events.band.business.use_case.musician.AssociateCreatedMusicianUseCase;
import br.com.events.band.business.use_case.musician.FindMusicianByCpfUseCase;
import br.com.events.band.data.io.musician.criteria.MusicianCriteria;
import br.com.events.band.data.io.musician.response.MusicianWithAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/musician")
@RequiredArgsConstructor
public class MusicianRestControllerV1 implements MusicianPort {

    private final AssociateCreatedMusicianUseCase associateCreatedMusicianUseCase;
    private final CreateMusicianUseCase createMusicianUseCase;
    private final FindMusicianByUuidUseCase findMusicianByUuidUseCase;
    private final FindMusicianByCpfUseCase findMusicianByCpfUseCase;
    private final FindMusiciansByCriteriaUseCase findMusiciansByCriteriaUseCase;
    private final DeleteMusiciansUseCase deleteMusiciansUseCase;
    private final UpdateMusicianUseCase updateMusicianUseCase;
    private final RemoveMusicianAvatarUseCase removeMusicianAvatarUseCase;

    @Override
    @PostMapping("/band/{bandUuid}/associate-created")
    public ResponseEntity<UuidHolderDTO> associate(
            @PathVariable String bandUuid, @RequestParam String musicianCpf
    ) {
        var result = associateCreatedMusicianUseCase.execute(bandUuid, musicianCpf);
        return ResponseEntity.ok(result);
    }

    @Override
    @PostMapping(value = "/band/{bandUuid}", consumes = "multipart/form-data")
    public ResponseEntity<UuidHolderDTO> create(
            @PathVariable("bandUuid") String bandUuid,
            @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture,
            @RequestPart("request") @RequestBody MusicianRequest musician
    ) {
        var response = createMusicianUseCase.execute(profilePicture, musician, bandUuid);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{musicianUuid}")
    public ResponseEntity<MusicianWithAddressResponse> findByUuid(@PathVariable String musicianUuid) {
        var response = findMusicianByUuidUseCase.execute(musicianUuid);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/cpf/{musicianCpf}")
    public ResponseEntity<MusicianWithAddressResponse> findByCpf(@PathVariable("musicianCpf") String musicianCpf) {
        var response = findMusicianByCpfUseCase.execute(musicianCpf);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<MusicianWithAddressResponse>> findByCriteria(
            Pageable pageable, MusicianCriteria criteria
    ) {
        var result = findMusiciansByCriteriaUseCase.execute(pageable, criteria);

        return ResponseEntity.ok(result);
    }

    @Override
    @DeleteMapping("/{musicianUuid}")
    public ResponseEntity<Void> delete(
            @PathVariable("musicianUuid") String musicianUuid
    ) {
        deleteMusiciansUseCase.execute(musicianUuid);

        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping("/{musicianUuid}")
    public ResponseEntity<Void> update(
            @PathVariable("musicianUuid") String musicianUuid,
            @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture,
            @RequestBody MusicianRequest musicianForm
    ) {
        updateMusicianUseCase.execute(musicianUuid, musicianForm);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{musicianUuid}/avatar")
    public ResponseEntity<Void> removeMusicianAvatar(@PathVariable("musicianUuid") String uuid) {
        removeMusicianAvatarUseCase.execute(uuid);
        return ResponseEntity.ok().build();
    }
}
