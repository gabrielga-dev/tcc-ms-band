package br.com.events.band.newer.adapter.port.rest.v1;

import br.com.events.band.newer.adapter.port.BandPort;
import br.com.events.band.newer.business.use_case.band.CreateBandUseCase;
import br.com.events.band.newer.business.use_case.band.FindAuthenticatedPersonBandsUseCase;
import br.com.events.band.newer.business.use_case.band.FindBandProfileUseCase;
import br.com.events.band.newer.business.use_case.band.FindBandsUseCase;
import br.com.events.band.newer.business.use_case.band.RemoveBandProfilePictureUseCase;
import br.com.events.band.newer.business.use_case.band.ToggleBandActivityFlagUseCase;
import br.com.events.band.newer.business.use_case.band.UpdateBandUseCase;
import br.com.events.band.newer.data.io.band.criteria.AuthenticatedPersonBandsCriteria;
import br.com.events.band.newer.data.io.band.criteria.FindBandsCriteria;
import br.com.events.band.newer.data.io.band.request.BandRequest;
import br.com.events.band.newer.data.io.band.request.UpdateBandRequest;
import br.com.events.band.newer.data.io.band.response.BandProfileResponse;
import br.com.events.band.newer.data.io.band.response.BandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * This interface dictates all available endpoints and its swagger documentation
 *
 * @author Gabriel Guimarães de Almeida
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/band")
public class BandControllerV1 implements BandPort {

    private final CreateBandUseCase createBandUseCase;
    private final FindAuthenticatedPersonBandsUseCase findAuthenticatedPersonBandsUseCase;
    private final FindBandsUseCase findBandsUseCase;
    private final UpdateBandUseCase updateBandUseCase;
    private final FindBandProfileUseCase findBandProfileUseCase;
    private final ToggleBandActivityFlagUseCase toggleBandActivityFlagUseCase;
    private final RemoveBandProfilePictureUseCase removeBandProfilePictureUseCase;

    @Override
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<URI> create(
            @RequestPart("profilePicture") MultipartFile profilePicture,
            @ModelAttribute @Valid BandRequest bandForm
    ) {
        var result = createBandUseCase.execute(bandForm, profilePicture);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(result.getUuid())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @GetMapping("/my-bands")
    public ResponseEntity<Page<BandResponse>> findAuthenticatedPersonBands(
            Pageable pageable, AuthenticatedPersonBandsCriteria criteria
    ) {
        var result = findAuthenticatedPersonBandsUseCase.execute(criteria, pageable);

        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<BandResponse>> findBands(Pageable pageable, FindBandsCriteria criteria) {
        var result = findBandsUseCase.execute(criteria, pageable);

        return ResponseEntity.ok(result);
    }

    @Override
    @PutMapping("/{bandUuid}")
    public ResponseEntity<Void> update(
            @PathVariable("bandUuid") String bandUuid,
            @RequestPart("profilePicture") MultipartFile profilePicture,
            @ModelAttribute @Valid UpdateBandRequest request
    ) {
        updateBandUseCase.execute(bandUuid, request, profilePicture);

        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/profile/{bandUuid}")
    public ResponseEntity<BandProfileResponse> findProfile(@PathVariable("bandUuid") String bandUuid) {
        var result = findBandProfileUseCase.execute(bandUuid);

        return ResponseEntity.ok(result);
    }

    @Override
    @DeleteMapping("/uuid/{bandUuid}")
    public ResponseEntity<Void> toggleBandActivity(@PathVariable("bandUuid") String bandUuid) {
        toggleBandActivityFlagUseCase.execute(bandUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/uuid/{bandUuid}/picture")
    public ResponseEntity<Void> removeProfilePicture(@PathVariable String bandUuid) {
        removeBandProfilePictureUseCase.execute(bandUuid);
        return ResponseEntity.noContent().build();
    }
}
