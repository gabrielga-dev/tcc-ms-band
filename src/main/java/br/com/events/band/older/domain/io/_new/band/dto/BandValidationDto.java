package br.com.events.band.older.domain.io._new.band.dto;

import br.com.events.band.newer.data.io.band.request.BandRequest;
import br.com.events.band.older.domain.type.MethodValidationType;
import lombok.Getter;

@Getter
public class BandValidationDto {

    private final BandRequest form;
    private final MethodValidationType methodType;

    public BandValidationDto(BandRequest form, MethodValidationType methodType){
        this.form = form;
        this.methodType = methodType;
    }
}
