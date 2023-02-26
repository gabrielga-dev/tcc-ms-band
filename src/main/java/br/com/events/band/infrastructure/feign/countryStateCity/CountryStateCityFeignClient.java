package br.com.events.band.infrastructure.feign.countryStateCity;

import br.com.events.band.application.config.feign.CountryStateCityFeignClientConfiguration;
import br.com.events.band.domain.io.feign.countryStateCity.getCitiesByStateAndCountryIso2.out.GetCitiesByStateAndCountryByIso2CountryStateCityFeignResult;
import br.com.events.band.domain.io.feign.countryStateCity.getCountries.out.GetCountriesCountryStateCityFeignResult;
import br.com.events.band.domain.io.feign.countryStateCity.getStatesByCountryIso2.out.GetStatesByCountryByIso2CountryStateCityFeignResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * This interface communicates with this <a href="https://countrystatecity.in/docs/">API</a>
 *
 * @author Gabriel Guimarães de Almeida
 */
@FeignClient(
    name = "country-state-city",
    url = "${feign.client.country.state.city.url}",
    configuration = CountryStateCityFeignClientConfiguration.class
)
public interface CountryStateCityFeignClient {

    @GetMapping("/countries")
    ResponseEntity<List<GetCountriesCountryStateCityFeignResult>> getCountries();

    @GetMapping("/countries/{countryIso}/states")
    ResponseEntity<List<GetStatesByCountryByIso2CountryStateCityFeignResult>> getStatesByCountryIso2(
        @PathVariable("countryIso") String countryIso
    );

    @GetMapping("/countries/{countryIso}/states/{stateIso}/cities")
    ResponseEntity<List<GetCitiesByStateAndCountryByIso2CountryStateCityFeignResult>> getCitiesByStateAndCountryIso2(
        @PathVariable("countryIso") String countryIso,
        @PathVariable("stateIso") String stateIso
    );
}
