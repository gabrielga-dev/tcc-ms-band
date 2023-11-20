package br.com.events.band.business.command.band;

import br.com.events.band.business.command.address.BuildAddressResponseCommand;
import br.com.events.band.data.io.band.response.BandResponse;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildBandResponseCommand {

    private final BuildAddressResponseCommand buildAddressResponseCommand;

    public BandResponse execute(BandTable band) {
        var address = buildAddressResponseCommand.execute(band.getAddress());

        var toReturn = new BandResponse(band);
        toReturn.setAddress(address);

        return toReturn;
    }
}
