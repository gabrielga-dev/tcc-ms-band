package br.com.events.band.domain.io.band.create.useCase.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * This class holds every needed information about the address of the new band at the use case layer
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class AddressCreateBandUseCaseForm {

    private String street;
    private String neighbour;
    private String complement;
    private Long cityId;
    private String stateIso;
    private String countryIso;
    private String zipCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
