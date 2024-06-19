package br.com.events.band.business.use_case.quote_request;

import br.com.events.band.data.io.quote_request.response.complete.CompleteBriefQuoteRequestResponse;

public interface FindQuoteRequestByUuidUseCase {

    CompleteBriefQuoteRequestResponse execute(String quoteRequestUuid);
}
