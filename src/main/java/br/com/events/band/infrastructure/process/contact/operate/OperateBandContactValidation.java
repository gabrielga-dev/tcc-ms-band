package br.com.events.band.infrastructure.process.contact.operate;

import br.com.events.band.domain.io.process.contact.operate.OperateBandContactDTO;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcess;

/**
 * This interface extends the {@link BaseVoidReturnProcess} and dictates to it that the incoming data type will be
 * {@link OperateBandContactDTO}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface OperateBandContactValidation extends BaseVoidReturnProcess<OperateBandContactDTO> {

}
