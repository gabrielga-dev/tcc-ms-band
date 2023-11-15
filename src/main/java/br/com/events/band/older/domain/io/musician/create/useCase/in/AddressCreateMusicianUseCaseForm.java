package br.com.events.band.older.domain.io.musician.create.useCase.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed information about the address of the new band
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class AddressCreateMusicianUseCaseForm {

    private String street;
    private String neighbour;
    private String complement;
    private Long cityId;
    private String stateIso;
    private String countryIso;
    private String zipCode;
}
