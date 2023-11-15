package br.com.events.band.older.domain.io.band.findBands.rest.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BandAddressFindBandsRestResult {

    private String street;
    private String neighbour;
    private String complement;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
