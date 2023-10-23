package br.com.events.band.infrastructure.process.band;

import br.com.events.band.domain.io._new.band.dto.BandValidationDto;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcess;

/**
 * This interface extends the {@link BaseVoidReturnProcess} and dictates to it that the incoming data type will be
 * {@link BandValidationDto}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface BandMethodValidation extends BaseVoidReturnProcess<BandValidationDto> {

}
