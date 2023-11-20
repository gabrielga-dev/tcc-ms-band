package br.com.events.band.business.use_case.music.impl;

import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.data.io.commom.UuidHolderDTO;
import br.com.events.band.business.command.music.FindMusicCommand;
import br.com.events.band.business.command.music.SaveMusicCommand;
import br.com.events.band.business.use_case.music.UpdateMusicUseCase;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.music.MusicNonExistenceException;
import br.com.events.band.data.io.music.request.MusicRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateMusicUseCaseImpl implements UpdateMusicUseCase {

    private final FindMusicCommand findMusicCommand;
    private final SaveMusicCommand saveMusicCommand;

    @Override
    public UuidHolderDTO execute(String bandUuid, String musicUuid, MusicRequest request) {
        var music = findMusicCommand.byUuidAndBandUuid(musicUuid, bandUuid)
                .orElseThrow(MusicNonExistenceException::new);

        if (!music.getContributingBand().getOwnerUuid().equals(AuthUtil.getAuthenticatedPersonUuid())) {
            throw new BandOwnerException();
        }

        music.update(request);

        music = saveMusicCommand.execute(music);

        return new UuidHolderDTO(music.getUuid());
    }
}
