package br.com.events.band.infrastructure.useCase.musician;

import br.com.events.band.domain.io.UuidHolderDTO;
import br.com.events.band.domain.io._new.musician.form.MusicianForm;

/**
 * This interface dictates that is needed a {@link MusicianForm} object to create, and it will return
 *  a {@link UuidHolderDTO} object
 * a new musician
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateMusicianUseCase {

    UuidHolderDTO execute(MusicianForm musician, String bandUuid);
}
