package br.com.events.band.domain.io.musician.update.useCase.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressUpdateMusicianUseCaseForm {

    private String street;
    private String neighbour;
    private String complement;
    private Long cityId;
    private String stateIso;
    private String countryIso;
    private String zipCode;
}
