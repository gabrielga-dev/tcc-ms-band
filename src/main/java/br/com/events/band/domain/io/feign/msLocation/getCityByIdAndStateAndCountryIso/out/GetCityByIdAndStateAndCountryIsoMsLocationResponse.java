package br.com.events.band.domain.io.feign.msLocation.getCityByIdAndStateAndCountryIso.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetCityByIdAndStateAndCountryIsoMsLocationResponse {

    private Long id;
    private String name;
}
