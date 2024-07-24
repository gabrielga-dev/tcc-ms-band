package br.com.events.band.data.io.quote.response.quote_quantity;

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
public class QuoteQuantityDashboardPartResponse {

    private final List<String> labels;
    private final String label;
    @JsonProperty("fill")
    private static final boolean FILL = true;
    @JsonProperty("backgroundColor")
    private static final String BACKGROUND_COLOR = "#33cc33";
    private final List<QuoteQuantityOfYearDashboardPartResponse> data;

    public QuoteQuantityDashboardPartResponse(List<QuoteTable> quotes) {
        var portugueseMonths = new DateFormatSymbols(new Locale("pt", "BR")).getMonths();
        this.labels = Stream.of(Month.values())
                .map(month -> {
                    String nomeMes = portugueseMonths[month.getValue() - 1];
                    return nomeMes.substring(0, 1).toUpperCase() + nomeMes.substring(1).toLowerCase();
                })
                .collect(Collectors.toList());

        this.label = "Quantidade de eventos";
        this.data = quotes.stream()
                .collect(
                        Collectors.groupingBy(
                                quoteTable -> quoteTable.getCreationDate().getYear(),
                                HashMap::new,
                                Collectors.toList()
                        )
                ).entrySet().stream()
                .map(
                        entrySet -> new QuoteQuantityOfYearDashboardPartResponse(
                                entrySet.getKey(), entrySet.getValue()
                        )
                ).collect(Collectors.toList());
    }
}
