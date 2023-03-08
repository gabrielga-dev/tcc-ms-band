package br.com.events.band.domain.io.band.findBands.rest.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindBandsRestResult {

    private String uuid;
    private String name;
    private String description;
    private BandAddressFindBandsRestResult address;
    private Integer numberOfMusicians;
    private Integer numberOfMusics;
}
