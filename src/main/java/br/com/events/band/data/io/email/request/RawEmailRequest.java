package br.com.events.band.data.io.email.request;

import br.com.events.band.core.util.DateUtil;
import br.com.events.band.data.io.event.response.EventProfileResponse;
import br.com.events.band.data.io.quote_request.request.QuoteRequestRequest;
import br.com.events.band.data.model.table.band.BandTable;
import br.com.events.band.data.model.table.quote.QuoteTable;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Getter
@ToString
public class RawEmailRequest implements Serializable {

    private final EmailRequestType type;
    private final Map<String, String> keyAndValues;

    public RawEmailRequest(
            QuoteRequestRequest request, EventProfileResponse event, BandTable bandTable, String ownerEmail
    ) {
        this.type = EmailRequestType.NEW_BAND_QUOTE_REQUEST;
        var eventDate = event.getDate();

        this.keyAndValues = Map.of(
                "email", ownerEmail,
                "bandUuid", bandTable.getUuid(),
                "bandName", bandTable.getName(),
                "eventName", event.getName(),
                "eventAddress", event.getFormattedAddress(),
                "city", event.getAddress().getCity(),
                "state", event.getAddress().getState(),
                "eventDate", DateUtil.formatDate(eventDate),
                "eventTime", DateUtil.formatTime(eventDate.toLocalTime()),
                "description", request.getDescription()
        );
    }

    public RawEmailRequest(QuoteTable quote, EventProfileResponse event, String ownerEmail, boolean hired) {
        if (hired) {
            this.type = EmailRequestType.QUOTE_HIRED;
        } else {
            this.type = EmailRequestType.QUOTE_DECLINED;

        }

        this.keyAndValues = Map.of(
                "email", ownerEmail,
                "businessName", quote.getQuoteRequest().getBand().getName(),
                "businessTypeName", "banda",
                "eventName", event.getName()
        );
    }
}
