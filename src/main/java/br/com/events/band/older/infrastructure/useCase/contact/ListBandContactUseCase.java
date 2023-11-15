package br.com.events.band.older.infrastructure.useCase.contact;

import br.com.events.band.older.domain.io.contact.listBandContact.useCase.out.ListBandContactUseCaseResult;
import br.com.events.band.older.infrastructure.useCase.UseCaseBase;

import java.util.List;

/**
 * This interface dictates that will be returned a {@link List} of {@link ListBandContactUseCaseResult}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ListBandContactUseCase extends UseCaseBase<String, List<ListBandContactUseCaseResult>> {
}
