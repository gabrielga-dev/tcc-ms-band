package br.com.events.band.application.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.events.band.domain.io.band.create.rest.in.CreateBandRestForm;
import br.com.events.band.domain.mapper.band.CreateBandMapper;
import br.com.events.band.infrastructure.controller.v1.BandControllerV1Doc;
import br.com.events.band.infrastructure.useCase.band.CreateBandUseCase;
import lombok.RequiredArgsConstructor;

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

    @Override
    @PostMapping
    public ResponseEntity<URI> create(@RequestBody @Valid CreateBandRestForm bandRestForm) {
        var mappedForm = CreateBandMapper.toUseCaseForm(bandRestForm);

        var result = createBandUseCase.execute(mappedForm);

        var mappedResult = CreateBandMapper.toRestResult(result);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").buildAndExpand(mappedResult).toUri();
        return ResponseEntity.created(uri).build();
    }
}
