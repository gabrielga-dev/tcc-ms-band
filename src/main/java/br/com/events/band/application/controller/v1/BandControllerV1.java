package br.com.events.band.application.controller.v1;

import br.com.events.band.domain.io.band.create.rest.in.CreateBandRestForm;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.in.FindAuthenticatedPersonBandsRestFilters;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.out.FindAuthenticatedPersonBandsRestResult;
import br.com.events.band.domain.io.band.findBands.rest.in.FindBandsRestFilters;
import br.com.events.band.domain.io.band.findBands.rest.out.FindBandsRestResult;
import br.com.events.band.domain.io.band.findByUuid.rest.out.FindBandByUuidRestResult;
import br.com.events.band.domain.io.band.update.rest.in.UpdateBandRestForm;
import br.com.events.band.domain.mapper.band.CreateBandMapper;
import br.com.events.band.domain.mapper.band.FindAuthenticatedPersonBandsMapper;
import br.com.events.band.domain.mapper.band.FindBandByUuidMapper;
import br.com.events.band.domain.mapper.band.FindBandsMapper;
import br.com.events.band.domain.mapper.band.UpdateBandMapper;
import br.com.events.band.infrastructure.controller.v1.BandControllerV1Doc;
import br.com.events.band.infrastructure.useCase.band.CreateBandUseCase;
import br.com.events.band.infrastructure.useCase.band.FindAuthenticatedPersonBands;
import br.com.events.band.infrastructure.useCase.band.FindBandByUuidUseCase;
import br.com.events.band.infrastructure.useCase.band.FindBandsUseCase;
import br.com.events.band.infrastructure.useCase.band.UpdateBandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
public class BandControllerV1 implements BandControllerV1Doc {

    private final CreateBandUseCase createBandUseCase;
    private final FindAuthenticatedPersonBands findAuthenticatedPersonBands;
    private final FindBandsUseCase findBandsUseCase;
    private final UpdateBandUseCase updateBandUseCase;
    private final FindBandByUuidUseCase findBandByUuidUseCase;

    private final FindAuthenticatedPersonBandsMapper findAuthenticatedPersonBandsMapper;
    private final FindBandsMapper findBandsMapper;
    private final FindBandByUuidMapper findBandByUuidMapper;

    @Override
    @PostMapping
    public ResponseEntity<URI> create(@RequestBody @Valid CreateBandRestForm bandRestForm) {
        var mappedForm = CreateBandMapper.toUseCaseForm(bandRestForm);

        var result = createBandUseCase.execute(mappedForm);

        var mappedResult = CreateBandMapper.toRestResult(result);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").buildAndExpand(mappedResult).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @GetMapping("/my-bands")
    public ResponseEntity<Page<FindAuthenticatedPersonBandsRestResult>> findAuthenticatedPersonBands(
            Pageable pageable, FindAuthenticatedPersonBandsRestFilters filters
    ) {
        var mappedFilters = FindAuthenticatedPersonBandsMapper.toUseCaseFilters(pageable, filters);

        var result = findAuthenticatedPersonBands.execute(mappedFilters);

        var mappedResult = result.map(findAuthenticatedPersonBandsMapper::toRestControllerResult);

        return ResponseEntity.ok(mappedResult);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<FindBandsRestResult>> findBands(
            Pageable pageable, FindBandsRestFilters filters
    ) {
        var mappedFilter = FindBandsMapper.toUseCaseFilter(filters, pageable);

        var result = findBandsUseCase.execute(mappedFilter);

        var mappedResult = result.map(findBandsMapper::toRestControllerResult);

        return ResponseEntity.ok(mappedResult);
    }

    @Override
    @PutMapping("/{bandUuid}")
    public ResponseEntity<Void> update(
            @PathVariable("bandUuid") String bandUuid, @RequestBody @Valid UpdateBandRestForm form
    ) {
        var mappedForm = UpdateBandMapper.toUseCaseForm(bandUuid, form);

        updateBandUseCase.execute(mappedForm);

        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/uuid/{bandUuid}")
    public ResponseEntity<FindBandByUuidRestResult> findByUuid(@PathVariable("bandUuid") String bandUuid) {
        var result = findBandByUuidUseCase.execute(bandUuid);

        var mappedResult = findBandByUuidMapper.from(result);

        return ResponseEntity.ok(mappedResult);
    }
}
