package br.com.events.band.data.io.quote_request.request;

import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AcceptQuoteRequestRequestMock {

    public static AcceptQuoteRequestRequest build() {
        var toReturn = new AcceptQuoteRequestRequest();

        toReturn.setFinalValue(MockConstants.BIG_DECIMAL);
        toReturn.setObservation(MockConstants.STRING);
        toReturn.setMusicianUuids(new ArrayList<>(List.of(MockConstants.STRING)));

        return toReturn;
    }
}
