package br.com.events.band.older.infrastructure.process.musician;

import br.com.events.band.older.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.older.infrastructure.process.BaseVoidReturnProcessCaller;

/**
 * This class dictates that is a {@link MusicianValidationDto} is going to be validated
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface MusicianMethodValidator extends BaseVoidReturnProcessCaller<MusicianValidationDto> {
}
