package br.com.events.band.infrastructure.useCase.musician;

import br.com.events.band.domain.io.musician.list.useCase.out.ListMusiciansUseCaseResult;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

import java.util.List;

public interface ListMusiciansUseCase extends UseCaseBase<String, List<ListMusiciansUseCaseResult>> {
}
