package br.com.events.band.data.model.table.quote_request;


import br.com.events.band.MockConstants;
import br.com.events.band.data.model.table.music.MusicTableMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuoteRequestMusicTableMock {

    public static QuoteRequestMusicTable build() {
        var toReturn = new QuoteRequestMusicTable();

        toReturn.setMusic(MusicTableMock.build());
        toReturn.setOrderNumber(MockConstants.INTEGER);
        toReturn.setObservation(MockConstants.STRING);

        return toReturn;
    }
}
