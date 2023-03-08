package br.com.events.band.domain.io.band.findBands.rest.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindBandsRestFilters {

    private String name;
    private Long cityId;
    private String stateIso;
    private String countryIso;
}
