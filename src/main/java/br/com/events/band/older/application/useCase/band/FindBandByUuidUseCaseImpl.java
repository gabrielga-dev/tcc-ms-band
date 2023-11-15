package br.com.events.band.older.application.useCase.band;

import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.older.domain.io.band.findByUuid.useCase.out.FindBandByUuidUseCaseResult;
import br.com.events.band.older.domain.mapper.band.FindBandByUuidMapper;
import br.com.events.band.older.domain.repository.BandRepository;
import br.com.events.band.older.infrastructure.useCase.band.FindBandByUuidUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBandByUuidUseCaseImpl implements FindBandByUuidUseCase {

    private final BandRepository bandRepository;

    @Override
    public FindBandByUuidUseCaseResult execute(String bandUuid) {
        var band = bandRepository.findById(bandUuid)
                .orElseThrow(BandNonExistenceException::new);

        return FindBandByUuidMapper.from(band);
    }
}
