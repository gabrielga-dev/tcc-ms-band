package br.com.events.band.business.use_case.musician_type.impl;

import br.com.events.band.business.command.musician_type.FindMusicianTypeCommand;
import br.com.events.band.business.use_case.musician_type.FindAllMusicianTypeUseCase;
import br.com.events.band.data.io.musician_type.response.MusicianTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllMusicianTypeUseCaseImpl implements FindAllMusicianTypeUseCase {

    private final FindMusicianTypeCommand findMusicianTypeCommand;

    @Override
    public List<MusicianTypeResponse> execute() {
        return findMusicianTypeCommand.findAll()
                .stream()
                .map(MusicianTypeResponse::new)
                .collect(Collectors.toList());
    }
}
