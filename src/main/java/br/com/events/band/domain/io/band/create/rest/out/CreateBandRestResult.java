package br.com.events.band.domain.io.band.create.rest.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateBandRestResult {

    private String bandUuid;
}
