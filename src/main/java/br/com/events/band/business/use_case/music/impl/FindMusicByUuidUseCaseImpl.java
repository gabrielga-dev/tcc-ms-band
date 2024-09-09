package br.com.events.band.business.use_case.music.impl;

import br.com.events.band.business.command.music.FindMusicCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.business.use_case.music.FindMusicByUuidUseCase;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.music.MusicNonExistenceException;
import br.com.events.band.data.io.music.response.MusicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindMusicByUuidUseCaseImpl implements FindMusicByUuidUseCase {

    private final AuthService authService;
    private final FindMusicCommand findMusicCommand;

    @Override
    public MusicResponse execute(String uuid) {
        var music = findMusicCommand.byUuid(uuid).orElseThrow(MusicNonExistenceException::new);

        if (!authService.getAuthenticatedPersonUuid().equals(music.getContributingBand().getOwnerUuid())) {
            throw new BandOwnerException();
        }

        return new MusicResponse(music);
    }
}
