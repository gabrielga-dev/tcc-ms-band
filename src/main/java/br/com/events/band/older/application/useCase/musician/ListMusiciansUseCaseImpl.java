package br.com.events.band.older.application.useCase.musician;

import br.com.events.band.older.domain.io._new.musician.response.MusicianResponse;
import br.com.events.band.older.domain.repository.MusicianRepository;
import br.com.events.band.older.infrastructure.useCase.musician.ListMusiciansUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListMusiciansUseCaseImpl implements ListMusiciansUseCase {

    private final MusicianRepository musicianRepository;

    @Override
    public List<MusicianResponse> execute(String bandUuid) {
        var musicians = musicianRepository.findByBandUuidAndActiveTrue(bandUuid);

        return musicians.stream()
                .map(MusicianResponse::new)
                .collect(Collectors.toList());
    }
}
