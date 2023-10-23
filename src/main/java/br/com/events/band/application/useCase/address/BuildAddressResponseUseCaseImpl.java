package br.com.events.band.application.useCase.address;

import br.com.events.band.domain.entity.address.MusicianAddress;
import br.com.events.band.domain.io._new.address.response.AddressResponse;
import br.com.events.band.infrastructure.feign.msLocation.LocationFeignClient;
import br.com.events.band.infrastructure.useCase.address.BuildAddressResponseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildAddressResponseUseCaseImpl implements BuildAddressResponseUseCase {

    private final LocationFeignClient locationFeignClient;

    @Override
    public AddressResponse execute(MusicianAddress address) {
        var city = locationFeignClient.getCityByIdAndStateAndCountryIso(
                address.getCountry(), address.getState(), address.getCity()
        );
        return new AddressResponse(address, city);
    }
}
