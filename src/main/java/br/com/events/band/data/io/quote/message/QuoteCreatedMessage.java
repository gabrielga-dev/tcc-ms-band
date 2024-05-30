package br.com.events.band.data.io.quote.message;

import br.com.events.band.data.model.table.quote.QuoteTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class QuoteCreatedMessage {

    private String msBandQuoteRequestUuid;
    private String msBandQuoteUuid;

    private String businessType;
    private String businessName;

    private String msEventQuoteUuid;
    private String msEventEventUuid;
    private BigDecimal price;
    private String observation;

    public QuoteCreatedMessage(QuoteTable quote) {
        this.msBandQuoteRequestUuid = quote.getQuoteRequest().getUuid();
        this.msBandQuoteUuid = quote.getUuid();

        this.businessType = "BAND";
        this.businessName = quote.getQuoteRequest().getBand().getName();

        this.msEventQuoteUuid = quote.getQuoteRequest().getOriginQuoteRequestUuid();
        this.msEventEventUuid = quote.getQuoteRequest().getEventUuid();

        this.price = quote.getPrice();
        this.observation = quote.getObservation();
    }
}
