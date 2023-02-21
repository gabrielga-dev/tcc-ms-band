package br.com.events.band.domain.io.feign.countryStateCity.getCountries.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every returned information at the <a
 * href="https://countrystatecity.in/docs/api/all-countries/">country state city API</a>
 *
 * @author Gabriel Guiamrães de Almeida
 */
@Getter
@Setter
@Builder
public class GetCountriesCountryStateCityFeignResult {

    private Long id;
    private String name;
    private String iso2;
}
