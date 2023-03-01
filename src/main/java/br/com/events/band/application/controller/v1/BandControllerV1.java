package br.com.events.band.application.controller.v1;

import br.com.events.band.domain.io.band.create.rest.in.CreateBandRestForm;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.in.FindAuthenticatedPersonBandsRestFilters;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.out.FindAuthenticatedPersonBandsRestResult;
import br.com.events.band.domain.mapper.band.CreateBandMapper;
import br.com.events.band.domain.mapper.band.FindAuthenticatedPersonBandsMapper;
import br.com.events.band.infrastructure.controller.v1.BandControllerV1Doc;
import br.com.events.band.infrastructure.useCase.band.CreateBandUseCase;
import br.com.events.band.infrastructure.useCase.band.FindAuthenticatedPersonBands;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

        var mappedResult = result.map(FindAuthenticatedPersonBandsMapper::toRestControllerResult);

        return ResponseEntity.ok(mappedResult);
    }
}
