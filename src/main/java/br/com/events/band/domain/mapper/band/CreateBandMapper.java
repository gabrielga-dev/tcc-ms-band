package br.com.events.band.domain.mapper.band;

import br.com.events.band.domain.entity.Band;
import br.com.events.band.domain.entity.address.BandAddress;
import br.com.events.band.domain.io.auth.AuthenticatedPerson;
import br.com.events.band.domain.io.band.create.rest.in.AddressCreateBandRestForm;
import br.com.events.band.domain.io.band.create.rest.in.CreateBandRestForm;
import br.com.events.band.domain.io.band.create.rest.out.CreateBandRestResult;
import br.com.events.band.domain.io.band.create.useCase.in.AddressCreateBandUseCaseForm;
import br.com.events.band.domain.io.band.create.useCase.in.CreateBandUseCaseForm;
import br.com.events.band.domain.io.band.create.useCase.out.CreateBandUseCaseResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

/**
 * This class holds every needed mapping method that is needed at band creation feature
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateBandMapper {

    public static CreateBandUseCaseForm toUseCaseForm(CreateBandRestForm bandRestForm) {
        return CreateBandUseCaseForm
            .builder()
            .name(bandRestForm.getName())
            .description(bandRestForm.getDescription())
            .address(address(bandRestForm.getAddress()))
            .build();
    }

    private static AddressCreateBandUseCaseForm address(final AddressCreateBandRestForm address) {
        return AddressCreateBandUseCaseForm
            .builder()
            .street(address.getStreet())
            .neighbour(address.getNeighbour())
            .complement(address.getComplement())
            .cityId(address.getCityId())
            .stateIso(address.getStateIso())
            .countryIso(address.getCountryIso())
            .zipCode(address.getZipCode())
            .latitude(null)//TODO latly add latitude filtering
            .longitude(null)//TODO latly add longitude filtering
            .build();
    }

    public static CreateBandRestResult toRestResult(final CreateBandUseCaseResult result) {
        return CreateBandRestResult
            .builder()
            .bandUuid(result.getBandUuid())
            .build();
    }

    public static Band toEntity(final CreateBandUseCaseForm bandUseCaseForm) {
        var band = new Band();
        band.setActive(true);
        band.setCreationDate(LocalDateTime.now());
        band.setName(bandUseCaseForm.getName());
        band.setDescription(bandUseCaseForm.getDescription());
        band.setAddress(address(band, bandUseCaseForm.getAddress()));
        band.setOwnerUuid(
            ((AuthenticatedPerson) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUuid()
        );
        return band;
    }

    private static BandAddress address(
        final Band band, final AddressCreateBandUseCaseForm address
    ) {
        var bandAddress = new BandAddress();
        bandAddress.setStreet(address.getStreet());
        bandAddress.setNeighbour(address.getNeighbour());
        bandAddress.setComplement(address.getComplement());
        bandAddress.setCity(address.getCityId());
        bandAddress.setState(address.getStateIso());
        bandAddress.setCountry(address.getCountryIso());
        bandAddress.setZipCode(address.getZipCode());
        bandAddress.setLatitude(address.getLatitude());
        bandAddress.setLongitude(address.getLongitude());
        bandAddress.setBand(band);
        return bandAddress;
    }

    public static CreateBandUseCaseResult toResult(final Band band) {
        return CreateBandUseCaseResult
            .builder()
            .bandUuid(band.getUuid())
            .build();
    }
}
