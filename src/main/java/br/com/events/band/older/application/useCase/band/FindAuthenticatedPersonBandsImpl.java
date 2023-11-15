package br.com.events.band.older.application.useCase.band;

import br.com.events.band.older.domain.io.band.findAuthenticatedPersonBands.useCase.in.FindAuthenticatedPersonBandsUseCaseFilters;
import br.com.events.band.older.domain.io.band.findAuthenticatedPersonBands.useCase.out.FindAuthenticatedPersonBandsUseCaseResult;
import br.com.events.band.older.domain.mapper.band.FindAuthenticatedPersonBandsMapper;
import br.com.events.band.older.domain.repository.BandRepository;
import br.com.events.band.older.infrastructure.useCase.band.FindAuthenticatedPersonBands;
import br.com.events.band.older.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAuthenticatedPersonBandsImpl implements FindAuthenticatedPersonBands {

    private final BandRepository bandRepository;

    @Override
    public Page<FindAuthenticatedPersonBandsUseCaseResult> execute(FindAuthenticatedPersonBandsUseCaseFilters param) {
        var authenticatedPersonUuid = AuthUtil.getAuthenticatedPerson().getUuid();

        var result = bandRepository.filterAuthenticatedBands(
                param.getPageable(),
                authenticatedPersonUuid,
                param.getName(),
                param.getCreationDateStart(),
                param.getCreationDateEnd()
        );

        return result.map(FindAuthenticatedPersonBandsMapper::toUseCaseResult);
    }
}
