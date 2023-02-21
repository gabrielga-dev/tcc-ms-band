package br.com.events.band.domain.io.band.create.useCase.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateBandUseCaseResult {

    private String bandUuid;
}
