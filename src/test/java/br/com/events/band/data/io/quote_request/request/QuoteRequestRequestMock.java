package br.com.events.band.data.io.quote_request.request;

import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuoteRequestRequestMock {

    public static QuoteRequestRequest build() {
        var toReturn = new QuoteRequestRequest();

        toReturn.setEventUuid(MockConstants.STRING);
        toReturn.setQuoteRequestUuid(MockConstants.STRING);
        toReturn.setBandUuid(MockConstants.STRING);
        toReturn.setDescription(MockConstants.STRING);
        toReturn.setPlaylist(new ArrayList<>(List.of(MusicQuoteRequestRequestMock.build())));
        toReturn.setMusicianTypes(new ArrayList<>(List.of(MusicianTypeQuoteRequestRequestMock.build())));

        return toReturn;
    }
}
