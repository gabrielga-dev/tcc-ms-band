package br.com.events.band.data.io.quote.response.quote_quantity;

import br.com.events.band.data.model.table.quote.QuoteTable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class QuoteQuantityOfYearDashboardPartResponse {

    private final Integer year;
    private final List<Long> values;

    public QuoteQuantityOfYearDashboardPartResponse(Integer year, List<QuoteTable> quotes) {
        this.year = year;
        var averagePrices = quotes.stream()
                .collect(Collectors.groupingBy(
                        quote -> quote.getCreationDate().getMonth(),
                        HashMap::new,
                        Collectors.counting()
                ));
        this.values = new ArrayList<>(averagePrices.values());
    }
}
