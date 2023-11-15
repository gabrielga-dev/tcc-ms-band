package br.com.events.band.older.domain.io.band.update.useCase.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every new information about the band at the use case layer
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class UpdateBandUseCaseForm {

    private String uuid;
    private String name;
    private String description;
    private AddressUpdateBandUseCaseForm address;
}
