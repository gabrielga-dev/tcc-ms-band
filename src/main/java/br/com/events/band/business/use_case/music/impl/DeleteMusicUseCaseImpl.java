package br.com.events.band.business.use_case.music.impl;

import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.business.command.music.FindMusicCommand;
import br.com.events.band.business.command.music.SaveMusicCommand;
import br.com.events.band.business.use_case.music.DeleteMusicUseCase;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.music.MusicNonExistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteMusicUseCaseImpl implements DeleteMusicUseCase {

    private final FindMusicCommand findMusicCommand;
    private final SaveMusicCommand saveMusicCommand;

    @Override
    public void execute(String bandUuid, String musicUuid) {
        var music = findMusicCommand.byUuidAndBandUuid(musicUuid, bandUuid)
                .orElseThrow(MusicNonExistenceException::new);

        if (!music.getContributingBand().getOwnerUuid().equals(AuthUtil.getAuthenticatedPersonUuid())) {
            throw new BandOwnerException();
        }

        music.deactivate();

        saveMusicCommand.execute(music);
    }
}
