package br.com.events.band.older.domain.io.band.findAuthenticatedPersonBands.useCase.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class BandAddressFindAuthenticatedPersonBandsUseCaseResult {

    private String street;
    private String neighbour;
    private String complement;
    private Long city;
    private String state;
    private String country;
    private String zipCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
