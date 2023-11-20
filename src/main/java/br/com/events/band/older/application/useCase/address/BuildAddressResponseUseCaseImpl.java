package br.com.events.band.older.application.useCase.address;

import br.com.events.band.newer.data.model.table.addresses.MusicianAddressTable;
import br.com.events.band.older.domain.io._new.address.response.AddressResponse;
import br.com.events.band.newer.adapter.feign.client.MsLocationFeignClient;
import br.com.events.band.older.infrastructure.useCase.address.BuildAddressResponseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildAddressResponseUseCaseImpl implements BuildAddressResponseUseCase {

    private final MsLocationFeignClient locationFeignClient;

    @Override
    public AddressResponse execute(MusicianAddressTable address) {
        var city = locationFeignClient.getCityByIdAndStateAndCountryIso(
                address.getCountry(), address.getState(), address.getCity()
        );
        return new AddressResponse(address, city);
    }
}
