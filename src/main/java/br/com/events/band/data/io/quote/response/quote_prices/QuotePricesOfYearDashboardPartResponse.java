package br.com.events.band.data.io.quote.response.quote_prices;

import br.com.events.band.data.model.table.quote.QuoteTable;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class QuotePricesOfYearDashboardPartResponse {

    private final Integer year;
    private final List<BigDecimal> values;

    public QuotePricesOfYearDashboardPartResponse(Integer year, List<QuoteTable> quotes) {
        this.year = year;

        this.values = new ArrayList<>(Arrays.asList(
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO
        ));

        quotes.forEach(
                quote -> {
                    var month = quote.getCreationDate().getMonthValue() - 1;
                    this.values.set(month, this.values.get(month).add(quote.getPrice()));
                }
        );
    }
}
