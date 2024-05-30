package br.com.events.band.business.use_case.quote_request;

import br.com.events.band.data.io.quote_request.request.AcceptQuoteRequestRequest;

public interface AcceptQuoteRequestUseCase {
    void execute(String quoteRequestUuid, AcceptQuoteRequestRequest request);
}
