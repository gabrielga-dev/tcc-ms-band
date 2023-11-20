package br.com.events.band.data.io.band.criteria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindBandsCriteria {

    private String name;
    private Long cityId;
    private String stateIso;
    private String countryIso;
}
