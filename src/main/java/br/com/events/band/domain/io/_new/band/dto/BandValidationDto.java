package br.com.events.band.domain.io._new.band.dto;

import br.com.events.band.domain.io._new.band.form.BandForm;
import br.com.events.band.domain.type.MethodValidationType;
import lombok.Getter;

@Getter
public class BandValidationDto {

    private final BandForm form;
    private final MethodValidationType methodType;

    public BandValidationDto(BandForm form, MethodValidationType methodType){
        this.form = form;
        this.methodType = methodType;
    }
}
