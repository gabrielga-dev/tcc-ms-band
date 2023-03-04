package br.com.events.band.domain.mapper.band;

import br.com.events.band.domain.entity.Band;
import br.com.events.band.domain.entity.address.BandAddress;
import br.com.events.band.domain.io.band.findBands.rest.in.FindBandsRestFilters;
import br.com.events.band.domain.io.band.findBands.rest.out.BandAddressFindBandsRestResult;
import br.com.events.band.domain.io.band.findBands.rest.out.FindBandsRestResult;
import br.com.events.band.domain.io.band.findBands.useCase.in.FindBandsUseCaseFilters;
import br.com.events.band.domain.io.band.findBands.useCase.out.BandAddressFindBandsUseCaseResult;
import br.com.events.band.domain.io.band.findBands.useCase.out.FindBandsUseCaseResult;
import br.com.events.band.domain.io.process.address.getCityName.in.GetCityNameByIdProcessRequest;
import br.com.events.band.infrastructure.process.address.cityName.GetCityNameByIdProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * This class holds every needed mapping method that is needed at band list
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class FindBandsMapper {

    private final GetCityNameByIdProcess getCityNameByIdProcess;

    public static FindBandsUseCaseFilters toUseCaseFilter(FindBandsRestFilters filters, Pageable pageable) {
        return FindBandsUseCaseFilters
                .builder()
                .pageable(pageable)
                .name(filters.getName())
                .latitude(
                        Objects.nonNull(filters.getLatitude())
                                ? filters.getLatitude().doubleValue()
                                : null
                ).longitude(
                        Objects.nonNull(filters.getLongitude())
                                ? filters.getLongitude().doubleValue()
                                : null
                )
                .distance(
                        Objects.nonNull(filters.getDistance())
                                ? filters.getDistance().doubleValue()
                                : null
                )
                .city(filters.getCity())
                .state(filters.getState())
                .country(filters.getCountry())
                .build();
    }

    public static FindBandsUseCaseResult toUseCaseFilter(Band band) {
        var mappedAddress = FindBandsMapper.toUseCaseResult(band.getAddress());

        return FindBandsUseCaseResult
                .builder()
                .uuid(band.getUuid())
                .name(band.getName())
                .description(band.getDescription())
                .address(mappedAddress)
                .numberOfMusicians(band.getMusicians().size())
                .numberOfMusics(band.getMusics().size())
                .build();
    }

    private static BandAddressFindBandsUseCaseResult toUseCaseResult(BandAddress address) {
        return BandAddressFindBandsUseCaseResult
                .builder()
                .street(address.getStreet())
                .neighbour(address.getNeighbour())
                .complement(address.getComplement())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .build();
    }


    public FindBandsRestResult toRestControllerResult(FindBandsUseCaseResult band) {
        var mappedAddress = toUseCaseResult(band.getAddress());

        return FindBandsRestResult
                .builder()
                .uuid(band.getUuid())
                .name(band.getName())
                .description(band.getDescription())
                .address(mappedAddress)
                .numberOfMusicians(band.getNumberOfMusicians())
                .numberOfMusics(band.getNumberOfMusics())
                .build();
    }

    private BandAddressFindBandsRestResult toUseCaseResult(BandAddressFindBandsUseCaseResult address) {
        var param = GetCityNameByIdProcessRequest
                .builder()
                .countryIso(address.getCountry())
                .stateIso(address.getState())
                .cityId(address.getCity())
                .build();

        return BandAddressFindBandsRestResult
                .builder()
                .street(address.getStreet())
                .neighbour(address.getNeighbour())
                .complement(address.getComplement())
                .city(getCityNameByIdProcess.execute(param))
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .build();
    }
}
