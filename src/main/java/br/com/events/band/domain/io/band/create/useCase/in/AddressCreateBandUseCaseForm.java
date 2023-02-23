package br.com.events.band.domain.io.band.create.useCase.in;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
