package br.com.events.band.older.infrastructure.controller.v1;

import br.com.events.band.older.domain.io.contact.createBandContact.rest.in.CreateBandContactRestForm;
import br.com.events.band.older.domain.io.contact.listBandContact.rest.out.ListBandContactRestResult;
import br.com.events.band.older.domain.io.contact.updateBandContact.rest.in.UpdateBandContactRestForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
    ResponseEntity<ListBandContactRestResult> createBandContact(String uuid, CreateBandContactRestForm bandRestForm);

    @ApiOperation(value = "Remove a band contact")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> removeBandContact(String bandUuid, String contactUuid);

    @ApiOperation(value = "Update a band contact")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> updateBandContact(String bandUuid, String contactUuid, UpdateBandContactRestForm form);

    @ApiOperation(value = "List all band contacts")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<List<ListBandContactRestResult>> listBandContact(String bandUuid);
}
