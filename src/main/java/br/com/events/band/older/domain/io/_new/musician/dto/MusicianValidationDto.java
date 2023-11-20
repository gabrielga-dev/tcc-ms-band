package br.com.events.band.older.domain.io._new.musician.dto;

import br.com.events.band.newer.data.io.musician.request.MusicianRequest;
import br.com.events.band.older.domain.type.MethodValidationType;
import lombok.Getter;

@Getter
public class MusicianValidationDto {

    private String bandUuid;
    private String musicianUuid;

    private final MethodValidationType methodType;
    private MusicianRequest form;

    public MusicianValidationDto(String bandUuid, MethodValidationType methodType, MusicianRequest form){
        this.bandUuid = bandUuid;
        this.methodType = methodType;
        this.form = form;
    }

    public MusicianValidationDto(String bandUuid, MethodValidationType methodType, String musicianUuid){
        this.bandUuid = bandUuid;
        this.methodType = methodType;
        this.musicianUuid = musicianUuid;
    }

    public MusicianValidationDto(
            String bandUuid, MethodValidationType methodType, String musicianUuid, MusicianRequest form
    ){
        this.bandUuid = bandUuid;
        this.musicianUuid = musicianUuid;
        this.methodType = methodType;
        this.form = form;
    }

    public MusicianValidationDto(
            MethodValidationType methodType, String musicianUuid
    ){
        this.musicianUuid = musicianUuid;
        this.methodType = methodType;
    }
}
