package br.com.events.band.business.use_case.music.impl;

import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.data.io.commom.UuidHolderDTO;
import br.com.events.band.data.model.table.MusicTable;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.music.SaveMusicCommand;
import br.com.events.band.business.use_case.music.ContributeMusicUseCase;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.data.io.music.request.MusicRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContributeMusicUseCaseImpl implements ContributeMusicUseCase {

    private final FindBandCommand findBandCommand;
    private final SaveMusicCommand saveMusicCommand;

    @Override
    public UuidHolderDTO execute(String bandUuid, MusicRequest request) {
        var band = findBandCommand.byUuidAndOwnerUuid(bandUuid, AuthUtil.getAuthenticatedPersonUuid())
                .orElseThrow(BandOwnerException::new);

        if (!band.isActive()) {
            throw new BandNonExistenceException();
        }

        var toSave = new MusicTable(request, band);

        toSave = saveMusicCommand.execute(toSave);

        return new UuidHolderDTO(toSave.getUuid());
    }
}
