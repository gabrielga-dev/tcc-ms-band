package br.com.events.band.older.domain.mapper.band;

import br.com.events.band.newer.data.model.table.BandTable;
import br.com.events.band.newer.data.model.table.MusicianTable;
import br.com.events.band.newer.data.model.table.addresses.BandAddressTable;
import br.com.events.band.newer.data.io.band.criteria.FindBandsCriteria;
import br.com.events.band.older.domain.io.band.findBands.rest.out.BandAddressFindBandsRestResult;
import br.com.events.band.older.domain.io.band.findBands.rest.out.FindBandsRestResult;
import br.com.events.band.older.domain.io.band.findBands.useCase.in.FindBandsUseCaseFilters;
import br.com.events.band.older.domain.io.band.findBands.useCase.out.BandAddressFindBandsUseCaseResult;
import br.com.events.band.older.domain.io.band.findBands.useCase.out.FindBandsUseCaseResult;
import br.com.events.band.older.domain.io.process.address.getCityName.in.GetCityNameByIdProcessRequest;
import br.com.events.band.older.infrastructure.process.address.cityName.GetCityNameByIdProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * This class holds every needed mapping method that is needed at band list
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class FindBandsMapper {

    private final GetCityNameByIdProcess getCityNameByIdProcess;

    public static FindBandsUseCaseFilters toUseCaseFilter(FindBandsCriteria filters, Pageable pageable) {
        return FindBandsUseCaseFilters
                .builder()
                .pageable(pageable)
                .name(filters.getName())
                .cityId(filters.getCityId())
                .stateIso(filters.getStateIso())
                .countryIso(filters.getCountryIso())
                .build();
    }

    public static FindBandsUseCaseResult toUseCaseFilter(BandTable band) {
        var mappedAddress = FindBandsMapper.toUseCaseResult(band.getAddress());

        return FindBandsUseCaseResult
                .builder()
                .uuid(band.getUuid())
                .name(band.getName())
                .description(band.getDescription())
                .profilePictureUuid(band.getProfilePictureUuid())
                .address(mappedAddress)
                .numberOfMusicians((int) band.getMusicians().stream().filter(MusicianTable::getActive).count())
                .numberOfMusics(band.getMusics().size())
                .build();
    }

    private static BandAddressFindBandsUseCaseResult toUseCaseResult(BandAddressTable address) {
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
                .profilePictureUuid(band.getProfilePictureUuid())
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
