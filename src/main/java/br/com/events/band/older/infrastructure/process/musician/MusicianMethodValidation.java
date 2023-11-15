package br.com.events.band.older.infrastructure.process.musician;

import br.com.events.band.older.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.older.infrastructure.process.BaseVoidReturnProcess;

/**
 * This interface extends the {@link BaseVoidReturnProcess} and dictates to it that the incoming data type will be
 * {@link MusicianValidationDto}
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface MusicianMethodValidation extends BaseVoidReturnProcess<MusicianValidationDto> {
}
