package br.com.events.band.domain.io.feign.countryStateCity.getCitiesByStateAndCountryIso2.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every returned information at the <a
 * href="https://countrystatecity.in/docs/api/cities-by-state-country/">country state city API</a>
 *
 * @author Gabriel Guiamrães de Almeida
 */
@Getter
@Setter
@Builder
public class GetCitiesByStateAndCountryByIso2CountryStateCityFeignResult {

    private Long id;
    private String name;
}
