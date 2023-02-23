package br.com.events.band.domain.mapper.service.location.location;

import br.com.events.band.domain.io.feign.countryStateCity.getCitiesByStateAndCountryIso2.out.GetCitiesByStateAndCountryByIso2CountryStateCityFeignResult;
import br.com.events.band.domain.io.feign.countryStateCity.getCountries.out.GetCountriesCountryStateCityFeignResult;
import br.com.events.band.domain.io.feign.countryStateCity.getStatesByCountryIso2.out.GetStatesByCountryByIso2CountryStateCityFeignResult;
import br.com.events.band.domain.io.location.in.CachedCountry;
import br.com.events.band.domain.io.location.in.CachedCountryState;
import br.com.events.band.domain.io.location.in.CachedCountryStateCity;
import br.com.events.band.infrastructure.service.LocationService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class maps every object related to location at {@link LocationService}
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CacheLocationMapper {

    /**
     * This method maps a {@link GetCountriesCountryStateCityFeignResult} into a {@link CachedCountry} object
     *
     * @param result {@link GetCountriesCountryStateCityFeignResult} object with the data to be mapped
     * @return {@link CachedCountry} object with the mapped information
     */
    public static CachedCountry toCached(GetCountriesCountryStateCityFeignResult result) {
        return CachedCountry
            .builder()
            .id(result.getId())
            .name(result.getName())
            .iso2(result.getIso2())
            .build();
    }

    /**
     * This method maps a {@link GetStatesByCountryByIso2CountryStateCityFeignResult} into a {@link CachedCountryState}
     * object
     *
     * @param result {@link GetStatesByCountryByIso2CountryStateCityFeignResult} object with the data to be mapped
     * @return {@link CachedCountryState} object with the mapped information
     */
    public static CachedCountryState toCached(GetStatesByCountryByIso2CountryStateCityFeignResult result) {
        return CachedCountryState
            .builder()
            .id(result.getId())
            .name(result.getName())
            .iso2(result.getIso2())
            .build();
    }

    /**
     * This method maps a {@link GetCitiesByStateAndCountryByIso2CountryStateCityFeignResult} into a
     * {@link CachedCountryStateCity} object
     *
     * @param result {@link GetCitiesByStateAndCountryByIso2CountryStateCityFeignResult} object with the data to be
     * mapped
     * @return {@link CachedCountryStateCity} object with the mapped information
     */
    public static CachedCountryStateCity toCached(GetCitiesByStateAndCountryByIso2CountryStateCityFeignResult result) {
        return CachedCountryStateCity
            .builder()
            .id(result.getId())
            .name(result.getName())
            .build();
    }
}
