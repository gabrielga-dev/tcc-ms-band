package br.com.events.band.newer.business.use_case.musician.impl;

import br.com.events.band.newer.business.command.address.BuildAddressResponseCommand;
import br.com.events.band.newer.business.command.musician.FindMusicianCommand;
import br.com.events.band.newer.business.use_case.musician.FindMusicianByCpfUseCase;
import br.com.events.band.newer.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.newer.data.io.musician.response.MusicianWithAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindMusicianByCpfUseCaseImpl implements FindMusicianByCpfUseCase {

    private final FindMusicianCommand findMusicianCommand;
    private final BuildAddressResponseCommand buildAddressResponseCommand;

    @Override
    public MusicianWithAddressResponse execute(String musicianCpf) {
        var musician = findMusicianCommand.byCpf(musicianCpf).orElseThrow(MusicianDoesNotExistException::new);
        var musicianAddress = buildAddressResponseCommand.execute(musician.getAddress());

        return new MusicianWithAddressResponse(musician, musicianAddress);
    }
}
