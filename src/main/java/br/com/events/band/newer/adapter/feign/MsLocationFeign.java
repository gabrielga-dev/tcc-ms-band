package br.com.events.band.newer.adapter.feign;

import br.com.events.band.newer.data.io.address.city.CityResponse;

public interface MsLocationFeign {

    void validateIfAddressExists(Long cityId, String stateIso, String countryIso);

    CityResponse getCityByIdAndStateAndCountryIso(String countryIso, String stateIso, Long cityId);
}
