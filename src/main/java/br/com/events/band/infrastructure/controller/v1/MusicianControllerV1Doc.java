package br.com.events.band.infrastructure.controller.v1;

import br.com.events.band.domain.io.UuidHolderDTO;
import br.com.events.band.domain.io._new.musician.form.MusicianForm;
import br.com.events.band.domain.io._new.musician.response.MusicianResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

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
    ResponseEntity<UuidHolderDTO> create(String bandUuid, MusicianForm musician);

    @ApiOperation(value = "Find musician by it's uuid")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<MusicianResponse> findByUuid(String bandUuid, String musicianUuid);

    @ApiOperation(value = "List the musicians of a band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<List<MusicianResponse>> list(String uuid);

    @ApiOperation(value = "Delete a musicians of a band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> delete(String bandUuid, String musicianUuid);

    @ApiOperation(value = "Updates a musician")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> update(String bandUuid, String musicianUuid, MusicianForm musicianRestForm);

    @ApiOperation(value = "Upload a musician's avatar")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<UuidHolderDTO> uploadMusicianAvatar(String uuid, MultipartFile avatar);

    @ApiOperation(value = "Remove the musician's avatar")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> removeMusicianAvatar(String uuid);
}
