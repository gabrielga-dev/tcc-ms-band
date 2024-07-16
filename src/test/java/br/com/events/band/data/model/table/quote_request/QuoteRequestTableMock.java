package br.com.events.band.data.model.table.quote_request;


import br.com.events.band.MockConstants;
import br.com.events.band.data.model.table.band.BandTableMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuoteRequestTableMock {

    public static QuoteRequestTable build() {
        var toReturn = new QuoteRequestTable();

        toReturn.setEventUuid(MockConstants.STRING);
        toReturn.setOriginQuoteRequestUuid(MockConstants.STRING);
        toReturn.setBand(BandTableMock.build());
        toReturn.setDescription(MockConstants.STRING);
        toReturn.setStatus(QuoteRequestStatusType.NON_ANSWERED);
        toReturn.setCreationDate(MockConstants.LOCAL_DATE_TIME);
        toReturn.setUpdateDate(MockConstants.LOCAL_DATE_TIME);
        toReturn.setWantedMusics(new ArrayList<>(List.of(QuoteRequestMusicTableMock.build())));
        toReturn.setWantedMusicianTypes(new ArrayList<>(List.of(QuoteRequestMusicianTypeTableMock.build())));

        return toReturn;
    }
}
