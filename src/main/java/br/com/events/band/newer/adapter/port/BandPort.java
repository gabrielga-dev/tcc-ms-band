package br.com.events.band.newer.adapter.port;

import br.com.events.band.older.domain.io.UuidHolderDTO;
import br.com.events.band.newer.data.io.band.request.BandRequest;
import br.com.events.band.newer.data.io.band.criteria.AuthenticatedPersonBandsCriteria;
import br.com.events.band.newer.data.io.band.response.BandResponse;
import br.com.events.band.newer.data.io.band.criteria.FindBandsCriteria;
import br.com.events.band.newer.data.io.band.response.BandProfileResponse;
import br.com.events.band.newer.data.io.band.request.UpdateBandRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

/**
 * This interface dictates which endpoints will be needed for implementation and holds which one's Swagger
 * documentation
 *
 * @author Gabriel Guimarães de Almeida
 */

@Api(tags = "Band Controller")
public interface BandPort {

    @ApiOperation(value = "Creates a new band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<URI> create(MultipartFile profilePicture, BandRequest bandForm);

    @ApiOperation(value = "Searches the bands that the authenticated person own")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Page<BandResponse>> findAuthenticatedPersonBands(
            Pageable pageable, AuthenticatedPersonBandsCriteria criteria
    );

    @ApiOperation(value = "Searches all bands")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Page<BandResponse>> findBands(Pageable pageable, FindBandsCriteria criteria);

    @ApiOperation(value = "Update a band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> update(String bandUuid, MultipartFile profilePicture, UpdateBandRequest request);

    @ApiOperation(value = "Searches for a band by its uuid")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<BandProfileResponse> findProfile(String bandUuid);

    @ApiOperation(value = "Toggle band's activity flag")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> toggleBandActivity(String bandUuid);

    @ApiOperation(value = "Uploads a new profile picture to thegiven band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<UuidHolderDTO> uploadProfilePicture(String bandUuid, MultipartFile file);

    @ApiOperation(value = "Remove the profile picture of the given band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> removeProfilePicture(String bandUuid);
}
