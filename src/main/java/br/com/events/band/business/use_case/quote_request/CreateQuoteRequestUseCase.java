package br.com.events.band.business.use_case.quote_request;

import br.com.events.band.data.io.quote_request.request.QuoteRequestRequest;

public interface CreateQuoteRequestUseCase {

    void execute(QuoteRequestRequest request);
}
