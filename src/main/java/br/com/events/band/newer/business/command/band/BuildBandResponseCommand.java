package br.com.events.band.newer.business.command.band;

import br.com.events.band.newer.adapter.feign.MsLocationFeign;
import br.com.events.band.newer.data.io.address.response.AddressResponse;
import br.com.events.band.newer.data.io.band.response.BandResponse;
import br.com.events.band.newer.data.table.BandTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildBandResponseCommand {

    private final MsLocationFeign msLocationFeign;

    public BandResponse execute(BandTable band) {
        var city = msLocationFeign.getCityByIdAndStateAndCountryIso(
                band.getAddress().getCountry(),
                band.getAddress().getState(),
                band.getAddress().getCity()
        );

        var address = new AddressResponse(band.getAddress(), city);

        var toReturn = new BandResponse(band);
        toReturn.setAddress(address);

        return toReturn;
    }
}
