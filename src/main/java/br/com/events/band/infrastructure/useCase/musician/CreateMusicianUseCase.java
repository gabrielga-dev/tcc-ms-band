package br.com.events.band.infrastructure.useCase.musician;

import br.com.events.band.domain.io.UuidHolderDTO;
import br.com.events.band.domain.io.musician.create.useCase.in.CreateMusicianUseCaseForm;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

/**
 * This interface dictates that is needed a {@link CreateMusicianUseCaseForm} object to create
 * a new musician
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateMusicianUseCase extends UseCaseBase<CreateMusicianUseCaseForm, UuidHolderDTO> {
}
