package br.com.events.band.business.use_case.quote_request;

import br.com.events.band.data.io.quote_request.criteria.FindQuoteRequestCriteria;
import br.com.events.band.data.io.quote_request.response.BriefQuoteRequestResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindBandsQuoteRequestsUseCase {
    Page<BriefQuoteRequestResponse> execute(String bandUuid, FindQuoteRequestCriteria criteria, Pageable pageable);
}
