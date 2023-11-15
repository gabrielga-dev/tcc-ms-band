package br.com.events.band.older.infrastructure.useCase.band;

import br.com.events.band.older.domain.io.UuidHolderDTO;
import br.com.events.band.newer.data.io.band.request.BandRequest;

/**
 * This interface dictates which classes are needed for create a new band
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateBandUseCase {

    UuidHolderDTO execute(BandRequest bandForm);
}
