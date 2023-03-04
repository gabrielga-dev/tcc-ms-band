package br.com.events.band.domain.io.band.findBands.rest.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class FindBandsRestFilters {

    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long distance;
    private String city;
    private String state;
    private String country;
}
