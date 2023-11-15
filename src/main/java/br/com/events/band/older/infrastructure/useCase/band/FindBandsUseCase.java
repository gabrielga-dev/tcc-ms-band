package br.com.events.band.older.infrastructure.useCase.band;

import br.com.events.band.older.domain.io.band.findBands.useCase.in.FindBandsUseCaseFilters;
import br.com.events.band.older.domain.io.band.findBands.useCase.out.FindBandsUseCaseResult;
import br.com.events.band.older.infrastructure.useCase.UseCaseBase;
import org.springframework.data.domain.Page;

public interface FindBandsUseCase extends UseCaseBase<FindBandsUseCaseFilters, Page<FindBandsUseCaseResult>> {
}
