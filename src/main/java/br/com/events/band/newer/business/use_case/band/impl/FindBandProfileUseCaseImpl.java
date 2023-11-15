package br.com.events.band.newer.business.use_case.band.impl;

import br.com.events.band.newer.business.command.address.BuildAddressResponseCommand;
import br.com.events.band.newer.business.command.band.FindBandCommand;
import br.com.events.band.newer.business.use_case.band.FindBandProfileUseCase;
import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.data.io.band.response.BandProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBandProfileUseCaseImpl implements FindBandProfileUseCase {

    private final FindBandCommand findBandCommand;
    private final BuildAddressResponseCommand buildAddressResponseCommand;

    @Override
    public BandProfileResponse execute(String bandUuid) {
        var band = findBandCommand.byUuid(bandUuid).orElseThrow(BandNonExistenceException::new);
        var address = buildAddressResponseCommand.execute(band.getAddress());

        return new BandProfileResponse(band, address);
    }
}
