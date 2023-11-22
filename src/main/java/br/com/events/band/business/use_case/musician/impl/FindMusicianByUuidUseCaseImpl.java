package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.business.use_case.musician.FindMusicianByUuidUseCase;
import br.com.events.band.business.command.address.BuildAddressResponseCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.data.io.musician.response.MusicianWithAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindMusicianByUuidUseCaseImpl implements FindMusicianByUuidUseCase {

    private final FindMusicianCommand findMusicianCommand;
    private final BuildAddressResponseCommand buildAddressResponseCommand;

    @Override
    public MusicianWithAddressResponse execute(String musicianUuid) {
        var musician = findMusicianCommand.byUuid(musicianUuid).orElseThrow(MusicianDoesNotExistException::new);
        var musicianAddress = buildAddressResponseCommand.execute(musician.getAddress());

        return new MusicianWithAddressResponse(musician, musicianAddress);
    }
}
