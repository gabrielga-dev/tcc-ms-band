package br.com.events.band.domain.mapper.band;


import br.com.events.band.domain.entity.Band;
import br.com.events.band.domain.entity.address.BandAddress;
import br.com.events.band.domain.io.band.update.rest.in.AddressUpdateBandRestForm;
import br.com.events.band.domain.io.band.update.rest.in.UpdateBandRestForm;
import br.com.events.band.domain.io.band.update.useCase.in.AddressUpdateBandUseCaseForm;
import br.com.events.band.domain.io.band.update.useCase.in.UpdateBandUseCaseForm;
import br.com.events.band.domain.io.process.band.update.UpdateBandProcessDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed mapping method that is needed at band update feature
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UpdateBandMapper {

    public static UpdateBandUseCaseForm toUseCaseForm(String bandUuid, UpdateBandRestForm form) {
        return UpdateBandUseCaseForm
                .builder()
                .uuid(bandUuid)
                .name(form.getName())
                .description(form.getDescription())
                .address(address(form.getAddress()))
                .build();
    }

    private static AddressUpdateBandUseCaseForm address(final AddressUpdateBandRestForm address) {
        return AddressUpdateBandUseCaseForm
                .builder()
                .street(address.getStreet())
                .neighbour(address.getNeighbour())
                .complement(address.getComplement())
                .cityId(address.getCityId())
                .stateIso(address.getStateIso())
                .countryIso(address.getCountryIso())
                .zipCode(address.getZipCode())
                .latitude(null)//TODO lately add latitude filtering
                .longitude(null)//TODO lately add longitude filtering
                .build();
    }

    public static UpdateBandProcessDTO toValidatorDto(Band entity, UpdateBandUseCaseForm form) {
        return UpdateBandProcessDTO
                .builder()
                .entity(entity)
                .useCaseForm(form)
                .build();
    }

    public static void transferData(Band toUpdate, UpdateBandUseCaseForm data) {
        toUpdate.setName(data.getName());
        toUpdate.setDescription(data.getDescription());
        UpdateBandMapper.transferData(toUpdate.getAddress(), data.getAddress());
    }

    private static void transferData(BandAddress address, AddressUpdateBandUseCaseForm data) {
        address.setStreet(data.getStreet());
        address.setNeighbour(data.getNeighbour());
        address.setComplement(data.getComplement());
        address.setCity(data.getCityId());
        address.setState(data.getStateIso());
        address.setCountry(data.getCountryIso());
        address.setZipCode(data.getZipCode());
        address.setLatitude(data.getLatitude());
        address.setLongitude(data.getLongitude());
    }
}
