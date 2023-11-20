package br.com.events.band.newer.business.command.address;

import br.com.events.band.newer.adapter.feign.MsLocationFeign;
import br.com.events.band.newer.data.io.address.IAddress;
import br.com.events.band.newer.data.io.address.response.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildAddressResponseCommand {

    private final MsLocationFeign msLocationFeign;

    public AddressResponse execute(IAddress address) {
        var city = msLocationFeign.getCityByIdAndStateAndCountryIso(
                address.getCountryIso(),
                address.getStateIso(),
                address.getCityId()
        );

        return new AddressResponse(address, city);
    }
}
