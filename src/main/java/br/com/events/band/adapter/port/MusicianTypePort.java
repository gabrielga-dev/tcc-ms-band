package br.com.events.band.adapter.port;

import br.com.events.band.data.io.musician_type.criteria.MusicianTypeCriteria;
import br.com.events.band.data.io.musician_type.response.MusicianTypeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Musician type Controller")
public interface MusicianTypePort {

    @ApiOperation(value = "List all available musician types by criteria")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<Page<MusicianTypeResponse>> findByCriteria(Pageable pageable, MusicianTypeCriteria criteria);

    @ApiOperation(value = "List all available musician types")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<List<MusicianTypeResponse>> findAll();
}
