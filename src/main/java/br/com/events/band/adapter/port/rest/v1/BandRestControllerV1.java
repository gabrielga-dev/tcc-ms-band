package br.com.events.band.adapter.port.rest.v1;

import br.com.events.band.adapter.port.BandPort;
import br.com.events.band.business.use_case.band.CreateBandUseCase;
import br.com.events.band.business.use_case.band.FindAllMusiciansUseCase;
import br.com.events.band.business.use_case.band.FindAllUserBandNamesUseCase;
import br.com.events.band.business.use_case.band.FindAuthenticatedPersonBandsUseCase;
import br.com.events.band.business.use_case.band.FindBandNamesUseCase;
import br.com.events.band.business.use_case.band.FindBandProfileUseCase;
import br.com.events.band.business.use_case.band.FindBandsUseCase;
import br.com.events.band.business.use_case.band.RemoveBandProfilePictureUseCase;
import br.com.events.band.business.use_case.band.ToggleBandActivityFlagUseCase;
import br.com.events.band.business.use_case.band.UpdateBandUseCase;
import br.com.events.band.business.use_case.quote_request.FindBandsQuoteRequestsUseCase;
import br.com.events.band.data.io.band.criteria.AuthenticatedPersonBandsCriteria;
import br.com.events.band.data.io.band.criteria.FindBandsCriteria;
import br.com.events.band.data.io.band.request.BandRequest;
import br.com.events.band.data.io.band.request.UpdateBandRequest;
import br.com.events.band.data.io.band.response.BandProfileResponse;
import br.com.events.band.data.io.band.response.BandResponse;
import br.com.events.band.data.io.musician.response.MusicianResponse;
import br.com.events.band.data.io.quote_request.criteria.FindQuoteRequestCriteria;
import br.com.events.band.data.io.quote_request.response.BriefQuoteRequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * This interface dictates all available endpoints and its swagger documentation
 *
 * @author Gabriel Guimarães de Almeida
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/band")
public class BandRestControllerV1 implements BandPort {

    private final CreateBandUseCase createBandUseCase;
    private final FindAuthenticatedPersonBandsUseCase findAuthenticatedPersonBandsUseCase;
    private final FindBandsUseCase findBandsUseCase;
    private final UpdateBandUseCase updateBandUseCase;
    private final FindBandProfileUseCase findBandProfileUseCase;
    private final ToggleBandActivityFlagUseCase toggleBandActivityFlagUseCase;
    private final RemoveBandProfilePictureUseCase removeBandProfilePictureUseCase;
    private final FindBandNamesUseCase findBandNamesUseCase;
    private final FindAllUserBandNamesUseCase findAllUserBandNamesUseCase;
    private final FindBandsQuoteRequestsUseCase findBandsQuoteRequestsUseCase;
    private final FindAllMusiciansUseCase findAllMusiciansUseCase;

    @Override
    @PreAuthorize("hasAuthority('BAND')")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<URI> create(
            @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture,
            @RequestPart("request") @Valid BandRequest request
    ) {
        var result = createBandUseCase.execute(request, profilePicture);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(result.getUuid())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @GetMapping("/my-bands")
    @PreAuthorize("hasAuthority('BAND')")
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
    @PreAuthorize("hasAuthority('BAND')")
    public ResponseEntity<Void> update(
            @PathVariable("bandUuid") String bandUuid,
            @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture,
            @RequestPart("request") @Valid UpdateBandRequest request
    ) {
        updateBandUseCase.execute(bandUuid, request, profilePicture);

        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{bandUuid}/profile")
    public ResponseEntity<BandProfileResponse> findProfile(@PathVariable("bandUuid") String bandUuid) {
        var result = findBandProfileUseCase.execute(bandUuid);

        return ResponseEntity.ok(result);
    }

    @Override
    @PreAuthorize("hasAuthority('BAND')")
    @PatchMapping("/{bandUuid}/toggle")
    public ResponseEntity<Void> toggleBandActivity(@PathVariable("bandUuid") String bandUuid) {
        toggleBandActivityFlagUseCase.execute(bandUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasAuthority('BAND')")
    @DeleteMapping("/{bandUuid}/picture")
    public ResponseEntity<Void> removeProfilePicture(@PathVariable String bandUuid) {
        removeBandProfilePictureUseCase.execute(bandUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/name")
    public ResponseEntity<Map<String, String>> getNames(@RequestParam("bandsUuids") List<String> bandsUuids) {
        var names = findBandNamesUseCase.execute(bandsUuids);
        return ResponseEntity.ok(names);
    }

    @Override
    @GetMapping("/all/name")
    @PreAuthorize("hasAuthority('BAND')")
    public ResponseEntity<Map<String, String>> getAllMyBandNames() {
        var names = findAllUserBandNamesUseCase.execute();
        return ResponseEntity.ok(names);
    }

    @Override
    @GetMapping("/{bandUuid}/quote-request")
    @PreAuthorize("hasAuthority('BAND')")
    public ResponseEntity<Page<BriefQuoteRequestResponse>> findQuoteRequests(
            @PathVariable String bandUuid, FindQuoteRequestCriteria criteria, Pageable pageable
    ) {
        var quoteRequests = findBandsQuoteRequestsUseCase.execute(bandUuid, criteria, pageable);
        return ResponseEntity.ok(quoteRequests);
    }

    @Override
    @GetMapping("/{bandUuid}/musicians")
    @PreAuthorize("hasAuthority('BAND')")
    public ResponseEntity<List<MusicianResponse>> findMusicians(@PathVariable("bandUuid") String bandUuid) {
        var quoteRequests = findAllMusiciansUseCase.execute(bandUuid);
        return ResponseEntity.ok(quoteRequests);
    }
}
