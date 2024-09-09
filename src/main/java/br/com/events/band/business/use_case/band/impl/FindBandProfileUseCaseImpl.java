package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.business.command.address.BuildAddressResponseCommand;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.business.use_case.band.FindBandProfileUseCase;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.data.io.band.response.BandProfileResponse;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBandProfileUseCaseImpl implements FindBandProfileUseCase {

    private final AuthService authService;
    private final FindBandCommand findBandCommand;
    private final BuildAddressResponseCommand buildAddressResponseCommand;

    @Override
    public BandProfileResponse execute(String bandUuid) {
        var band = findBandCommand.byUuid(bandUuid).orElseThrow(BandNonExistenceException::new);
        var isBandOwner = authService.isAuthenticated() &&
                authService.getAuthenticatedPersonUuid().equals(band.getOwnerUuid());

        if (this.cantSeeTheProfile(band, isBandOwner)) {
            throw new BandNonExistenceException();
        }

        var address = buildAddressResponseCommand.execute(band.getAddress());

        return new BandProfileResponse(band, address, isBandOwner);
    }

    private boolean cantSeeTheProfile(BandTable band, boolean isBandOwner) {
        return !isBandOwner && !band.isActive();
    }
}
