package br.com.events.band.infrastructure.controller.v1;

import br.com.events.band.domain.io.band.create.rest.in.CreateBandRestForm;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.in.FindAuthenticatedPersonBandsRestFilters;
import br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.out.FindAuthenticatedPersonBandsRestResult;
import br.com.events.band.domain.io.band.findBands.rest.in.FindBandsRestFilters;
import br.com.events.band.domain.io.band.findBands.rest.out.FindBandsRestResult;
import br.com.events.band.domain.io.band.update.rest.in.UpdateBandRestForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.net.URI;

/**
 * This interface dictates which endpoints will be needed for implementation and holds which one's Swagger
 * documentation
 *
 * @author Gabriel Guimarães de Almeida
 */

@Api(tags = "Band Controller")
public interface BandControllerV1Doc {

    @ApiOperation(value = "Creates a new band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<URI> create(CreateBandRestForm bandRestForm);

    @ApiOperation(value = "Searches the bands that the authenticated person own")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Page<FindAuthenticatedPersonBandsRestResult>> findAuthenticatedPersonBands(
            Pageable pageable, FindAuthenticatedPersonBandsRestFilters filters
    );

    @ApiOperation(value = "Searches all bands")
    ResponseEntity<Page<FindBandsRestResult>> findBands(Pageable pageable, FindBandsRestFilters filters);

    @ApiOperation(value = "Update a band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> update(String bandUuid, UpdateBandRestForm form);
}
