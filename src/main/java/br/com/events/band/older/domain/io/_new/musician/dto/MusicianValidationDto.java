package br.com.events.band.older.domain.io._new.musician.dto;

import br.com.events.band.older.domain.io._new.musician.form.MusicianForm;
import br.com.events.band.older.domain.type.MethodValidationType;
import lombok.Getter;

@Getter
public class MusicianValidationDto {

    private String bandUuid;
    private String musicianUuid;

    private final MethodValidationType methodType;
    private MusicianForm form;

    public MusicianValidationDto(String bandUuid, MethodValidationType methodType, MusicianForm form){
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
            String bandUuid, MethodValidationType methodType, String musicianUuid, MusicianForm form
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
