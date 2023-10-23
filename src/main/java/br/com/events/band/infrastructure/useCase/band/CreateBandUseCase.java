package br.com.events.band.infrastructure.useCase.band;

import br.com.events.band.domain.io.UuidHolderDTO;
import br.com.events.band.domain.io._new.band.form.BandForm;

/**
 * This interface dictates which classes are needed for create a new band
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateBandUseCase {

    UuidHolderDTO execute(BandForm bandForm);
}
