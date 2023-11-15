package br.com.events.band.older.infrastructure.process.band.update;

import br.com.events.band.older.domain.io.process.band.update.UpdateBandProcessDTO;
import br.com.events.band.older.infrastructure.process.BaseVoidReturnProcessCaller;

/**
 * This interface extends the {@link BaseVoidReturnProcessCaller} and dictates to it that the incoming data type will be
 * {@link UpdateBandProcessDTO}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface UpdateBandValidator extends BaseVoidReturnProcessCaller<UpdateBandProcessDTO> {

}
