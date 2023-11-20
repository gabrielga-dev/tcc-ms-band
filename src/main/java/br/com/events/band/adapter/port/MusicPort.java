package br.com.events.band.adapter.port;

import br.com.events.band.data.io.commom.UuidHolderDTO;
import br.com.events.band.data.io.music.request.MusicRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

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
    ResponseEntity<UuidHolderDTO> update(String bandUuid, String musicUuid, MusicRequest music);


    @ApiOperation(value = "Delete music")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Void> deleteMusic(String bandUuid, String musicUuid);
}
