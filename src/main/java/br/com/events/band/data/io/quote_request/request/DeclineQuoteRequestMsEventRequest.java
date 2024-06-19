package br.com.events.band.data.io.quote_request.request;

import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class DeclineQuoteRequestMsEventRequest implements Serializable {

    private final String businessName;
    private final String businessType = "BAND";

    public DeclineQuoteRequestMsEventRequest(QuoteRequestTable quoteRequest) {
        this.businessName = quoteRequest.getBand().getName();
    }
}
