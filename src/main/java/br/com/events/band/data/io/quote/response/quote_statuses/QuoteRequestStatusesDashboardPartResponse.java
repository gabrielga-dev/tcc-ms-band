package br.com.events.band.data.io.quote.response.quote_statuses;

import br.com.events.band.data.model.table.quote_request.QuoteRequestStatusType;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class QuoteRequestStatusesDashboardPartResponse {

    private final List<String> labels = List.of(
            "Contratados",
            "Não respondidos",
            "Negados"
    );
    private final List<String> backgroundColor = List.of(
        "#33cc33",
        "#e6e6e6",
        "#ff0000"
    );
    private final List<String> hoverBackgroundColor = List.of(
        "#29a329",
        "#cccccc",
        "#cc0000"
    );
    private final List<Long> data;

    public QuoteRequestStatusesDashboardPartResponse(List<QuoteRequestTable> quoteRequests){
        var quantityHired = quoteRequests.stream()
                .filter(qr -> Objects.nonNull(qr.getQuote()))
                .count();
        var quantityNotAnswered = quoteRequests.stream()
                .filter(qr -> Objects.equals(QuoteRequestStatusType.NON_ANSWERED, qr.getStatus()))
                .count();
        var quantityDeclined = quoteRequests.stream()
                .filter(qr -> Objects.equals(QuoteRequestStatusType.DECLINED, qr.getStatus()))
                .count();

        this.data = List.of(quantityHired, quantityNotAnswered, quantityDeclined);
    }
}
