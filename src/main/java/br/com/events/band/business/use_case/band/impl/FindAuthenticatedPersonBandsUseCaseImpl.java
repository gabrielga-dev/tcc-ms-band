package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.business.command.band.BuildBandResponseCommand;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.business.use_case.band.FindAuthenticatedPersonBandsUseCase;
import br.com.events.band.data.io.band.criteria.AuthenticatedPersonBandsCriteria;
import br.com.events.band.data.io.band.response.BandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAuthenticatedPersonBandsUseCaseImpl implements FindAuthenticatedPersonBandsUseCase {

    private final AuthService authService;
    private final FindBandCommand findBandCommand;
    private final BuildBandResponseCommand buildBandResponseCommand;

    @Override
    public Page<BandResponse> execute(AuthenticatedPersonBandsCriteria criteria, Pageable pageable) {
        var bands = findBandCommand.byPerson(authService.getAuthenticatedPersonUuid(), criteria, pageable);

        return bands.map(buildBandResponseCommand::execute);
    }
}
