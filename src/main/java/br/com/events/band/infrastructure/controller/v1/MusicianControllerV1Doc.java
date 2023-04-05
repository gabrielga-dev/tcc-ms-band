package br.com.events.band.infrastructure.controller.v1;

import br.com.events.band.domain.io.musician.create.rest.in.CreateMusicianRestForm;
import br.com.events.band.domain.io.musician.list.rest.out.ListMusiciansRestResult;
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
@Api(tags = "Musician Controller")
public interface MusicianControllerV1Doc {

    @ApiOperation(value = "Creates a new musician")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> create(String uuid, CreateMusicianRestForm bandRestForm);

    @ApiOperation(value = "List the musicians of a band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<List<ListMusiciansRestResult>> list(String uuid);

    @ApiOperation(value = "Delete a musicians of a band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> delete(String bandUuid, String musicianUuid);
}
