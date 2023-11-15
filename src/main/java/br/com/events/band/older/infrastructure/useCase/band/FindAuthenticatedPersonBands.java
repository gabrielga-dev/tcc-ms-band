package br.com.events.band.older.infrastructure.useCase.band;

import br.com.events.band.older.domain.io.band.findAuthenticatedPersonBands.useCase.in.FindAuthenticatedPersonBandsUseCaseFilters;
import br.com.events.band.older.domain.io.band.findAuthenticatedPersonBands.useCase.out.FindAuthenticatedPersonBandsUseCaseResult;
import br.com.events.band.older.infrastructure.useCase.UseCaseBase;
import org.springframework.data.domain.Page;

/**
 * This interface extends {@link UseCaseBase} and dictates that will enter a
 * {@link FindAuthenticatedPersonBandsUseCaseFilters} and return a
 * {@link Page}<{@link FindAuthenticatedPersonBandsUseCaseResult}>
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface FindAuthenticatedPersonBands
        extends UseCaseBase<
        FindAuthenticatedPersonBandsUseCaseFilters, Page<FindAuthenticatedPersonBandsUseCaseResult>
        > {
}
