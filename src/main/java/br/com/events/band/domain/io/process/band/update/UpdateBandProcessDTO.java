package br.com.events.band.domain.io.process.band.update;

import br.com.events.band.domain.entity.Band;
import br.com.events.band.domain.io.band.update.useCase.in.UpdateBandUseCaseForm;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateBandProcessDTO {

    private UpdateBandUseCaseForm useCaseForm;
    private Band entity;
}
