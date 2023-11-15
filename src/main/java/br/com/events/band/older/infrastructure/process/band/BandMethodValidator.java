package br.com.events.band.older.infrastructure.process.band;

import br.com.events.band.older.domain.io._new.band.dto.BandValidationDto;
import br.com.events.band.older.infrastructure.process.BaseVoidReturnProcessCaller;

/**
 * This interface extends the {@link BaseVoidReturnProcessCaller} and dictates to it that the incoming data type will be
 * {@link BandValidationDto}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface BandMethodValidator extends BaseVoidReturnProcessCaller<BandValidationDto> {

}
