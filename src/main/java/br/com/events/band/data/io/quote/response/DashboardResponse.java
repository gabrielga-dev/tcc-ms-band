package br.com.events.band.data.io.quote.response;

import br.com.events.band.data.io.quote.response.quote_prices.QuotePricesDashboardPartResponse;
import br.com.events.band.data.io.quote.response.quote_statuses.QuoteRequestStatusesDashboardPartResponse;
import br.com.events.band.data.io.quote_request.response.BriefQuoteRequestResponse;
import br.com.events.band.data.model.table.quote.QuoteTable;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DashboardResponse {

    private final List<BriefQuoteRequestResponse> allQuotes;
    private final QuotePricesDashboardPartResponse quotePrices;
    private final QuoteRequestStatusesDashboardPartResponse quoteStatuses;

    public DashboardResponse(List<QuoteTable> quotes) {
        this.allQuotes = quotes.parallelStream()
                .sorted(Comparator.comparing(QuoteTable::getCreationDate))
                .map(BriefQuoteRequestResponse::new)
                .collect(Collectors.toList());

        this.quotePrices = new QuotePricesDashboardPartResponse(quotes);
        this.quoteStatuses = new QuoteRequestStatusesDashboardPartResponse(
                quotes.parallelStream()
                        .map(QuoteTable::getQuoteRequest)
                        .collect(Collectors.toList())
        );

    }
}
