package br.com.events.band.infrastructure.controller.v1;

import java.net.URI;

import org.springframework.http.ResponseEntity;

import br.com.events.band.domain.io.band.create.rest.in.CreateBandRestForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This interface dictates which endpoints will be needed for implementation and holds which one's Swagger
 * documentation
 *
 * @author Gabriel Guimarães de Almeida
 */

@Api(tags = "Band Controller")
public interface BandControllerV1Doc {

    @ApiOperation(value = "Creates a new band")
    ResponseEntity<URI> create(CreateBandRestForm bandRestForm);
}
