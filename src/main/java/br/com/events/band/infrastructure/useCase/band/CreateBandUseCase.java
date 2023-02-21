package br.com.events.band.infrastructure.useCase.band;

import br.com.events.band.domain.io.band.create.useCase.in.CreateBandUseCaseForm;
import br.com.events.band.domain.io.band.create.useCase.out.CreateBandUseCaseResult;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

/**
 * This interface dictates which classes are needed for create a new band
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateBandUseCase extends UseCaseBase<CreateBandUseCaseForm, CreateBandUseCaseResult> {

}
