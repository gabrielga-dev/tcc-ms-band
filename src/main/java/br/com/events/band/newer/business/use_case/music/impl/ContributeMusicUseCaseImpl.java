package br.com.events.band.newer.business.use_case.music.impl;

import br.com.events.band.newer.business.command.band.FindBandCommand;
import br.com.events.band.newer.business.command.music.SaveMusicCommand;
import br.com.events.band.newer.business.use_case.music.ContributeMusicUseCase;
import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.newer.core.util.AuthUtil;
import br.com.events.band.newer.data.io.commom.UuidHolderDTO;
import br.com.events.band.newer.data.io.music.request.MusicRequest;
import br.com.events.band.newer.data.model.table.MusicTable;
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
