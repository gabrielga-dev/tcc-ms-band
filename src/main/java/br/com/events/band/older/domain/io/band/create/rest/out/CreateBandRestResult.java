package br.com.events.band.older.domain.io.band.create.rest.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds all needed information of the result of the create band feature at the rest controller layer
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class CreateBandRestResult {

    private String bandUuid;
}
