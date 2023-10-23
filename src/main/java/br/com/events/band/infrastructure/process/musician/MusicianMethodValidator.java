package br.com.events.band.infrastructure.process.musician;

import br.com.events.band.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.infrastructure.process.BaseVoidReturnProcessCaller;

/**
 * This class dictates that is a {@link MusicianValidationDto} is going to be validated
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface MusicianMethodValidator extends BaseVoidReturnProcessCaller<MusicianValidationDto> {
}
