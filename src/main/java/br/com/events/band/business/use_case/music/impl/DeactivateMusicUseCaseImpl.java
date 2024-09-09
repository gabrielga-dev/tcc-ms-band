package br.com.events.band.business.use_case.music.impl;

import br.com.events.band.business.command.music.FindMusicCommand;
import br.com.events.band.business.command.music.SaveMusicCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.business.use_case.music.DeactivateMusicUseCase;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.music.MusicNonExistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeactivateMusicUseCaseImpl implements DeactivateMusicUseCase {

    private final AuthService authService;
    private final FindMusicCommand findMusicCommand;
    private final SaveMusicCommand saveMusicCommand;

    @Override
    public void execute(String musicUuid) {
        var music = findMusicCommand.byUuid(musicUuid).orElseThrow(MusicNonExistenceException::new);

        if (!music.getContributingBand().getOwnerUuid().equals(authService.getAuthenticatedPersonUuid())) {
            throw new BandOwnerException();
        }

        music.deactivate();

        saveMusicCommand.execute(music);
    }
}
