package br.com.events.band.data.io.quote_request.request;

import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class DeclineQuoteRequestMsEventRequest implements Serializable {

    private final String businessName;
    @JsonProperty("businessType")
    private static final String BUSINESS_TYPE = "BAND";

    public DeclineQuoteRequestMsEventRequest(QuoteRequestTable quoteRequest) {
        this.businessName = quoteRequest.getBand().getName();
    }
}
