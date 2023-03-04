package br.com.events.band.domain.io.band.findBands.useCase.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BandAddressFindBandsUseCaseResult {

    private String street;
    private String neighbour;
    private String complement;
    private Long city;
    private String state;
    private String country;
    private String zipCode;
}
