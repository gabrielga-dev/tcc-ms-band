package br.com.events.band.adapter.port;

import br.com.events.band.data.io.commom.UuidHolderDTO;
import br.com.events.band.data.io.musician.criteria.MusicianCriteria;
import br.com.events.band.data.io.musician.request.MusicianRequest;
import br.com.events.band.data.io.musician.response.MusicianWithAddressResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * This interface dictates which endpoints will be needed for implementation and holds which one's Swagger
 * documentation
 *
 * @author Gabriel Guimarães de Almeida
 */
@Api(tags = "Musician Controller")
public interface MusicianPort {

    @ApiOperation(value = "Associate an already created musician to a band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<UuidHolderDTO> associate(String bandUuid, String musicianCpf);

    @ApiOperation(value = "Disassociate musician from a band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> disassociate(String bandUuid, String musicianUuid);

    @ApiOperation(value = "Creates a new musician")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<UuidHolderDTO> create(String bandUuid, MultipartFile profilePicture, MusicianRequest musician);

    @ApiOperation(value = "Find musician by it's uuid")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<MusicianWithAddressResponse> findByUuid(String musicianUuid);

    @ApiOperation(value = "Find musician by it's CPF")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<MusicianWithAddressResponse> findByCpf(String musicianCpf);

    @ApiOperation(value = "Find musicians by criteria")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Page<MusicianWithAddressResponse>> findByCriteria(Pageable pageable, MusicianCriteria criteria);

    @ApiOperation(value = "Delete a musicians of a band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> delete(String musicianUuid);

    @ApiOperation(value = "Updates a musician")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> update(String musicianUuid, MultipartFile profilePicture, MusicianRequest request);

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
