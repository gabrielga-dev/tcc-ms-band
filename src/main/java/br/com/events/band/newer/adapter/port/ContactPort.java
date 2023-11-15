package br.com.events.band.newer.adapter.port;

import br.com.events.band.newer.data.io.contact.request.ContactRequest;
import br.com.events.band.newer.data.io.contact.response.ContactResponse;
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
public interface ContactPort {

    @ApiOperation(value = "Creates a new band contact")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<ContactResponse> createBandContact(String uuid, ContactRequest request);

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
    ResponseEntity<Void> updateBandContact(String bandUuid, String contactUuid, ContactRequest request);

    @ApiOperation(value = "List all band contacts")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<List<ContactResponse>> listBandContact(String bandUuid);
}
