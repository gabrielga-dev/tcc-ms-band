package br.com.events.band.data.io.quote_request.response;

import br.com.events.band.core.util.DateUtil;
import br.com.events.band.data.model.table.quote.QuoteTable;
import br.com.events.band.data.model.table.quote_request.QuoteRequestStatusType;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import lombok.Getter;

import java.util.Objects;

@Getter
public class BriefQuoteRequestResponse {

    private final String quoteRequestUuid;
    private final String originQuoteRequestUuid;
    private final Long creationDate;
    private final QuoteRequestStatusType status;
    private final String eventUuid;
    private final boolean hired;


    public BriefQuoteRequestResponse(QuoteTable quoteRequestTable) {
        this(quoteRequestTable.getQuoteRequest());
    }

    public BriefQuoteRequestResponse(QuoteRequestTable quoteRequestTable) {
        this.quoteRequestUuid = quoteRequestTable.getUuid();
        this.originQuoteRequestUuid = quoteRequestTable.getOriginQuoteRequestUuid();
        this.creationDate = DateUtil.localDateTimeToMilliseconds(quoteRequestTable.getCreationDate());
        this.status = quoteRequestTable.getStatus();
        this.eventUuid = quoteRequestTable.getEventUuid();
        this.hired = Objects.nonNull(quoteRequestTable.getQuote());
    }
}
