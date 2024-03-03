package br.com.events.band.adapter.port;

import br.com.events.band.data.io.commom.UuidHolderDTO;
import br.com.events.band.data.io.music.criteria.MusicCriteria;
import br.com.events.band.data.io.music.request.MusicRequest;
import br.com.events.band.data.io.music.response.MusicResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Music Controller")
public interface MusicPort {

    @ApiOperation(value = "Creates a new music")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<UuidHolderDTO> contribute(String bandUuid, MusicRequest request);

    @ApiOperation(value = "Updates a music")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<UuidHolderDTO> update(String musicUuid, MusicRequest music);

    @ApiOperation(value = "Deactivate music")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> deactivate(String musicUuid);

    @ApiOperation(value = "Activate music")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> activate(String musicUuid);

    @ApiOperation(value = "Find music by criteria")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Page<MusicResponse>> findByCriteria(MusicCriteria criteria, Pageable pageable);

    @ApiOperation(value = "Find music by criteria")
    ResponseEntity<List<MusicResponse>> findBandMusics(String bandUuid);
}
