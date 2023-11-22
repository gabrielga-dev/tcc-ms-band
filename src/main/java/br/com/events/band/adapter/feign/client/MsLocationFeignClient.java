package br.com.events.band.adapter.feign.client;

import br.com.events.band.adapter.feign.MsLocationFeign;
import br.com.events.band.adapter.feign.client.config.MyEventFeignClientConfiguration;
import br.com.events.band.data.io.address.city.CityResponse;
import org.springframework.cloud.openfeign.FeignClient;
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
public interface MsLocationFeignClient extends MsLocationFeign {

    @GetMapping("/v1/location/check-address")
    void validateIfAddressExists(
            @RequestParam("cityId") Long cityId,
            @RequestParam("stateIso") String stateIso,
            @RequestParam("countryIso") String countryIso
    );

    @GetMapping("/v1/location/country/{countryIso}/state/{stateIso}/city/{cityId}")
    CityResponse getCityByIdAndStateAndCountryIso(
            @PathVariable("countryIso") String countryIso,
            @PathVariable("stateIso") String stateIso,
            @PathVariable("cityId") Long cityId
    );
}
