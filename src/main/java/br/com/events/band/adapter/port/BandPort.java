package br.com.events.band.adapter.port;

import br.com.events.band.data.io.band.criteria.AuthenticatedPersonBandsCriteria;
import br.com.events.band.data.io.band.criteria.FindBandsCriteria;
import br.com.events.band.data.io.band.request.BandRequest;
import br.com.events.band.data.io.band.request.UpdateBandRequest;
import br.com.events.band.data.io.band.response.BandProfileResponse;
import br.com.events.band.data.io.band.response.BandResponse;
import br.com.events.band.data.io.musician.response.MusicianResponse;
import br.com.events.band.data.io.quote_request.criteria.FindQuoteRequestCriteria;
import br.com.events.band.data.io.quote_request.response.BriefQuoteRequestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "Remove the profile picture of the given band")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> removeProfilePicture(String bandUuid);

    @ApiOperation(value = "Find the names of the bands with the given uuids")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Map<String, String>> getNames(List<String> bandsUuids);

    @ApiOperation(value = "Find the all names of all user's bands with the given uuids")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Map<String, String>> getAllMyBandNames();

    @ApiOperation(value = "Find band's all quote requests")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Page<BriefQuoteRequestResponse>> findQuoteRequests(
            String bandUuid, FindQuoteRequestCriteria criteria, Pageable pageable
    );

    @ApiOperation(value = "Find all band's musicians")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<List<MusicianResponse>> findMusicians(String bandUuid);
}
