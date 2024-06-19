package br.com.events.band.business.use_case.quote_request.impl;

import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.quote_request.FindBandsQuoteRequestsCommand;
import br.com.events.band.business.use_case.quote_request.FindBandsQuoteRequestsUseCase;
import br.com.events.band.core.exception.band.BandNotFoundException;
import br.com.events.band.data.io.quote_request.criteria.FindQuoteRequestCriteria;
import br.com.events.band.data.io.quote_request.response.BriefQuoteRequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBandsQuoteRequestsUseCaseImpl implements FindBandsQuoteRequestsUseCase {

    private final FindBandCommand findBandCommand;
    private final FindBandsQuoteRequestsCommand findBandsQuoteRequestsCommand;

    @Override
    public Page<BriefQuoteRequestResponse> execute(
            String bandUuid, FindQuoteRequestCriteria criteria, Pageable pageable
    ) {
        var band = findBandCommand.byUuid(bandUuid).orElseThrow(BandNotFoundException::new);
        if (!band.isActive()){
            throw new BandNotFoundException();
        }

        return findBandsQuoteRequestsCommand.execute(bandUuid, criteria, pageable)
                .map(BriefQuoteRequestResponse::new);
    }
}
