package br.com.events.band.data.io.quote_request.response;

import br.com.events.band.core.util.DateUtil;
import br.com.events.band.data.model.table.quote_request.QuoteRequestStatusType;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import lombok.Getter;

@Getter
public class BriefQuoteRequestResponse {

    private final String quoteRequestUuid;
    private final Long creationDate;
    private final QuoteRequestStatusType status;
    private final String eventUuid;


    public BriefQuoteRequestResponse(QuoteRequestTable quoteRequestTable){
        this.quoteRequestUuid = quoteRequestTable.getUuid();
        this.creationDate = DateUtil.localDateTimeToMilliseconds(quoteRequestTable.getCreationDate());
        this.status = quoteRequestTable.getStatus();
        this.eventUuid = quoteRequestTable.getEventUuid();
    }
}
