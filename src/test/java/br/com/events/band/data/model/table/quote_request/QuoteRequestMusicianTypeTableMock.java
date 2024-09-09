package br.com.events.band.data.model.table.quote_request;


import br.com.events.band.MockConstants;
import br.com.events.band.data.model.table.musician.MusicianTypeTableMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuoteRequestMusicianTypeTableMock {

    public static QuoteRequestMusicianTypeTable build() {
        var toReturn = new QuoteRequestMusicianTypeTable();

        toReturn.setQuantity(MockConstants.INTEGER);
        toReturn.setObservation(MockConstants.STRING);
        toReturn.setMusicianType(MusicianTypeTableMock.build());

        return toReturn;
    }
}
