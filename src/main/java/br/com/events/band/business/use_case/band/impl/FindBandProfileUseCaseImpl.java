package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.use_case.band.FindBandProfileUseCase;
import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.data.io.band.response.BandProfileResponse;
import br.com.events.band.business.command.address.BuildAddressResponseCommand;
import br.com.events.band.core.exception.band.BandNonExistenceException;
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
        if (!AuthUtil.getAuthenticatedPersonUuid().equals(band.getOwnerUuid()) && !band.isActive()) {
            throw new BandNonExistenceException();
        }

        var address = buildAddressResponseCommand.execute(band.getAddress());

        return new BandProfileResponse(band, address);
    }
}
