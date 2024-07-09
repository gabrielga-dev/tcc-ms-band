package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.business.command.band.BuildBandResponseCommand;
import br.com.events.band.business.use_case.band.FindBandsUseCase;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.data.io.band.criteria.FindBandsCriteria;
import br.com.events.band.data.io.band.response.BandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBandsUseCaseImpl implements FindBandsUseCase {

    private final FindBandCommand findBandCommand;
    private final BuildBandResponseCommand buildBandResponseCommand;

    @Override
    public Page<BandResponse> execute(FindBandsCriteria criteria, Pageable pageable) {
        var bands = findBandCommand.byCriteria(criteria, pageable);

        return bands.map(buildBandResponseCommand::execute);
    }
}
