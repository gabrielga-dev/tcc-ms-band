package br.com.events.band.application.service;

import br.com.events.band.domain.io.location.in.CachedCountryStateCity;
import br.com.events.band.domain.mapper.service.location.location.CacheLocationMapper;
import br.com.events.band.infrastructure.feign.countryStateCity.CountryStateCityFeignClient;
import br.com.events.band.infrastructure.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * This class implements the {@link LocationService} interface and holds every needed method to work with locations
 *
 * @author Gabriel Guimarães de Almeida
 */
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final CountryStateCityFeignClient countryStateCityFeignClient;

    @Override
    public Optional<CachedCountryStateCity> getCityByStateAndCountryIso2(
        final String countryIso2, final String stateIso2, final String city
    ) {
        var citiesResponse = countryStateCityFeignClient.getCitiesByStateAndCountryIso2(countryIso2, stateIso2);
        var cities = Objects.requireNonNull(citiesResponse.getBody());

        return cities.stream().filter(
                feignCity -> Objects.equals(city, feignCity.getName())
            ).findFirst()
            .map(
                CacheLocationMapper::toCached
            );
    }
}
