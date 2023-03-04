package br.com.events.band.application.useCase.band;

import br.com.events.band.domain.io.band.findBands.useCase.in.FindBandsUseCaseFilters;
import br.com.events.band.domain.io.band.findBands.useCase.out.FindBandsUseCaseResult;
import br.com.events.band.domain.mapper.band.FindBandsMapper;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.infrastructure.useCase.band.FindBandsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBandsUseCaseImpl implements FindBandsUseCase {

    private final BandRepository bandRepository;

    @Override
    public Page<FindBandsUseCaseResult> execute(FindBandsUseCaseFilters param) {

        var filteredBands = bandRepository.filterBands(
            param.getPageable(),
            param.getName(),
            param.getLatitude(),
            param.getLongitude(),
            param.getDistance(),
            param.getCity(),
            param.getState(),
            param.getCountry()
        );

        return filteredBands.map(FindBandsMapper::toUseCaseFilter);
    }
}
