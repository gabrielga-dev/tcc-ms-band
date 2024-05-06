package br.com.events.band.data.io.quote_request.request;

import lombok.Getter;

@Getter
public class QuoteRequestCreationErrorMessage {

    private final String quoteRequestUuid;

    public QuoteRequestCreationErrorMessage(QuoteRequestRequest request) {
        this.quoteRequestUuid = request.getQuoteRequestUuid();
    }
}
