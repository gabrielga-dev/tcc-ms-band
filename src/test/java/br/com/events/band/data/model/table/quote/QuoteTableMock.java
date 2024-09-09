package br.com.events.band.data.model.table.quote;

import br.com.events.band.MockConstants;
import br.com.events.band.data.model.table.musician.MusicianTableMock;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTableMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuoteTableMock {

    public static QuoteTable build() {
        var toReturn = new QuoteTable();

        toReturn.setStatus(QuoteStatusType.NON_ANSWERED);
        toReturn.setPrice(BigDecimal.TEN);
        toReturn.setObservation(MockConstants.STRING);
        toReturn.setHiredMusicians(new ArrayList<>(List.of(MusicianTableMock.build())));
        toReturn.setQuoteRequest(QuoteRequestTableMock.build());
        toReturn.setCreationDate(MockConstants.LOCAL_DATE_TIME);

        return toReturn;
    }
}
