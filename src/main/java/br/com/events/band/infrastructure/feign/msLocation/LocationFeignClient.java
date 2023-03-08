package br.com.events.band.infrastructure.feign.msLocation;

import br.com.events.band.application.config.feign.MyEventFeignClientConfiguration;
import br.com.events.band.domain.io.feign.msLocation.getCityByIdAndStateAndCountryIso.out.GetCityByIdAndStateAndCountryIsoMsLocationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This interface communicates with MS-LOCATION
 *
 * @author Gabriel Guimarães de Almeida
 */
@FeignClient(
        name = "location-ms-location",
        url = "${feign.client.ms.location.url}",
        configuration = MyEventFeignClientConfiguration.class
)
public interface LocationFeignClient {

    @GetMapping("/v1/location/check-address")
    ResponseEntity<Void> validateIfAddressExists(
            @RequestParam("cityId") Long cityId,
            @RequestParam("stateIso") String stateIso,
            @RequestParam("countryIso") String countryIso
    );

    @GetMapping("/v1/location/country/{countryIso}/state/{stateIso}/city/{cityId}")
    ResponseEntity<GetCityByIdAndStateAndCountryIsoMsLocationResponse> getCityByIdAndStateAndCountryIso(
            @PathVariable("countryIso") String countryIso,
            @PathVariable("stateIso") String stateIso,
            @PathVariable("cityId") Long cityId
    );
}
