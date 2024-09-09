package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.band.SaveBandCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.business.use_case.band.ToggleBandActivityFlagUseCase;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToggleBandActivityFlagUseCaseImpl implements ToggleBandActivityFlagUseCase {

    private final AuthService authService;
    private final FindBandCommand findBandCommand;
    private final SaveBandCommand saveBandCommand;

    @Override
    public void execute(String bandUuid) {
        var band = findBandCommand.byUuidAndOwnerUuid(
                bandUuid, authService.getAuthenticatedPersonUuid()
        ).orElseThrow(BandNonExistenceException::new);

        band.toggleActivity();

        saveBandCommand.execute(band);
    }
}
