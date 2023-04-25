package br.com.events.band.domain.io.band.findByUuid.useCase.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class BandAddressFindBandByUuidUseCaseResult {

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
