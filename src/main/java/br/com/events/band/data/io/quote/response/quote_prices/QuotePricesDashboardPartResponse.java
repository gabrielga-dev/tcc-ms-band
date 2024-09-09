package br.com.events.band.data.io.quote.response.quote_prices;

import br.com.events.band.data.model.table.quote.QuoteTable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class QuotePricesDashboardPartResponse {

    private final List<String> labels;
    private final String label;
    @JsonProperty("backgroundColor")
    private static final String BACKGROUND_COLOR = "#33cc33";
    private final List<QuotePricesOfYearDashboardPartResponse> data;

    public QuotePricesDashboardPartResponse(List<QuoteTable> quotes) {
        var portugueseMonths = new DateFormatSymbols(new Locale("pt", "BR")).getMonths();
        this.labels = Stream.of(Month.values())
                .map(month -> {
                    String nomeMes = portugueseMonths[month.getValue() - 1];
                    return nomeMes.substring(0, 1).toUpperCase() + nomeMes.substring(1).toLowerCase();
                })
                .collect(Collectors.toList());

        this.label = "Faturamento dos orçamentos";
        this.data = quotes.stream()
                .collect(
                        Collectors.groupingBy(
                                quoteTable -> quoteTable.getCreationDate().getYear(),
                                HashMap::new,
                                Collectors.toList()
                        )
                ).entrySet().stream()
                .map(
                        entrySet -> new QuotePricesOfYearDashboardPartResponse(
                                entrySet.getKey(), entrySet.getValue()
                        )
                ).collect(Collectors.toList());
    }
}
