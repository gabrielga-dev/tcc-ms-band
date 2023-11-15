package br.com.events.band.older.infrastructure.useCase.band;

import br.com.events.band.older.domain.io.band.findByUuid.useCase.out.FindBandByUuidUseCaseResult;
import br.com.events.band.older.infrastructure.useCase.UseCaseBase;

public interface FindBandByUuidUseCase extends UseCaseBase<String, FindBandByUuidUseCaseResult> {
}
