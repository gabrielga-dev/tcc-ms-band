package br.com.events.band.older.domain.io.band.create.useCase.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds all needed information of the result of the create band feature at the use case layer
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class CreateBandUseCaseResult {

    private String bandUuid;
}
