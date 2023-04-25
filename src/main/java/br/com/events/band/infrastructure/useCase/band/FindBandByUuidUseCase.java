package br.com.events.band.infrastructure.useCase.band;

import br.com.events.band.domain.io.band.findByUuid.useCase.out.FindBandByUuidUseCaseResult;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

public interface FindBandByUuidUseCase extends UseCaseBase<String, FindBandByUuidUseCaseResult> {
}
