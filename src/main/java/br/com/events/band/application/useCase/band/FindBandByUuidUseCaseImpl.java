package br.com.events.band.application.useCase.band;

import br.com.events.band.application.process.exception.BandNonExistenceException;
import br.com.events.band.domain.io.band.findByUuid.useCase.out.FindBandByUuidUseCaseResult;
import br.com.events.band.domain.mapper.band.FindBandByUuidMapper;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.infrastructure.useCase.band.FindBandByUuidUseCase;
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
