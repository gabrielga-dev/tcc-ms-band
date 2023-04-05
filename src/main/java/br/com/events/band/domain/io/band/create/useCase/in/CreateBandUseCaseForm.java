package br.com.events.band.domain.io.band.create.useCase.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed information about the new band at the use case layer
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class CreateBandUseCaseForm {

    private String name;
    private String description;
    private AddressCreateBandUseCaseForm address;
}
