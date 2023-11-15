package br.com.events.band.newer.business.command.address;

import br.com.events.band.newer.adapter.feign.MsLocationFeign;
import br.com.events.band.newer.data.io.address.response.AddressResponse;
import br.com.events.band.newer.data.table.addresses.BandAddressTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildAddressResponseCommand {

    private final MsLocationFeign msLocationFeign;

    public AddressResponse execute(BandAddressTable address) {
        var city = msLocationFeign.getCityByIdAndStateAndCountryIso(
                address.getCountry(),
                address.getState(),
                address.getCity()
        );

        return new AddressResponse(address, city);
    }
}
