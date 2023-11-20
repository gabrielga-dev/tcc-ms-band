package br.com.events.band.newer.business.use_case.band.impl;

import br.com.events.band.newer.business.command.band.FindBandCommand;
import br.com.events.band.newer.business.command.band.SaveBandCommand;
import br.com.events.band.newer.business.use_case.band.RemoveBandProfilePictureUseCase;
import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoveBandProfilePictureUseCaseImpl implements RemoveBandProfilePictureUseCase {

    private final FindBandCommand findBandCommand;
    private final SaveBandCommand saveBandCommand;

    @Override
    public void execute(String bandUuid) {
        var band = findBandCommand.byUuidAndOwnerUuid(
                bandUuid, AuthUtil.getAuthenticatedPersonUuid()
        ).orElseThrow(BandNonExistenceException::new);

        band.removeProfilePicture();

        saveBandCommand.execute(band);
    }
}
