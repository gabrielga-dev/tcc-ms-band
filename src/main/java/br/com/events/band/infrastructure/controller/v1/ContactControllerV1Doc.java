package br.com.events.band.infrastructure.controller.v1;

import br.com.events.band.domain.io.contact.createBandContact.rest.in.CreateBandContactRestForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

/**
 * This interface dictates which endpoints will be needed for implementation and holds which one's Swagger
 * documentation
 *
 * @author Gabriel Guimarães de Almeida
 */

@Api(tags = "Contact Controller")
public interface ContactControllerV1Doc {

    @ApiOperation(value = "Creates a new band contact")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> createBandContact(String uuid, CreateBandContactRestForm bandRestForm);

    @ApiOperation(value = "Remove a band contact")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
        ResponseEntity<Void> removeBandContact(String bandUuid, String contactUuid);
}
