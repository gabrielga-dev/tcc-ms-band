package br.com.events.band.infrastructure.process.contact.operate;

import br.com.events.band.domain.io.process.contact.operate.OperateBandContactDTO;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcessCaller;

/**
 * This class dictates that is a {@link OperateBandContactDTO} is going to be validated
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface OperateBandContactValidator extends BaseVoidReturnProcessCaller<OperateBandContactDTO> {
}
