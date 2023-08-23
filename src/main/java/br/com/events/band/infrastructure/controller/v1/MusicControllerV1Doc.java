package br.com.events.band.infrastructure.controller.v1;

import br.com.events.band.domain.io.music.create.in.CreateMusicForm;
import br.com.events.band.domain.io.music.create.out.CreateMusicResult;
import br.com.events.band.domain.io.musicSheet.create.rest.out.CreateMusicSheetResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "Music Controller")
public interface MusicControllerV1Doc {

    @ApiOperation(value = "Creates a new music")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<CreateMusicResult> create(String bandUuid, CreateMusicForm bandRestForm);

    @ApiOperation(value = "Creates a new sheet music for a music")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<CreateMusicSheetResult> createSheet(
            String musicUuid, String observation, MultipartFile file
    );
}
