package br.com.events.band.newer.business.use_case.band.impl;

import br.com.events.band.newer.business.command.band.FindBandCommand;
import br.com.events.band.newer.business.use_case.band.FindAuthenticatedPersonBandsUseCase;
import br.com.events.band.newer.core.util.AuthUtil;
import br.com.events.band.newer.data.io.band.criteria.AuthenticatedPersonBandsCriteria;
import br.com.events.band.newer.data.io.band.response.BandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAuthenticatedPersonBandsUseCaseImpl implements FindAuthenticatedPersonBandsUseCase {

    private final FindBandCommand findBandCommand;

    @Override
    public Page<BandResponse> execute(AuthenticatedPersonBandsCriteria criteria, Pageable pageable) {
        var authenticatedPersonUuid = AuthUtil.getAuthenticatedPerson().getUuid();

        var bands = findBandCommand.byPerson(authenticatedPersonUuid, criteria, pageable);

        return bands.map(BandResponse::new);
    }
}
