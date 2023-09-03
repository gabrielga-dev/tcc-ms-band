package br.com.events.band.application.useCase.musician;

import br.com.events.band.domain.io.musician.list.useCase.out.ListMusiciansUseCaseResult;
import br.com.events.band.domain.mapper.musician.ListMusicianMapper;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.useCase.musician.ListMusiciansUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListMusiciansUseCaseImpl implements ListMusiciansUseCase {

    private final MusicianRepository musicianRepository;

    @Override
    public List<ListMusiciansUseCaseResult> execute(String bandUuid) {
        var musicians = musicianRepository.findByBandUuidAndActiveTrue(bandUuid);
        return ListMusicianMapper.from(musicians);
    }
}
