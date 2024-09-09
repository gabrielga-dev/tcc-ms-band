package br.com.events.band.data.model.table.musician;


import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicianTypeTableMock {

    public static MusicianTypeTable build() {
        var toReturn = new MusicianTypeTable();

        toReturn.setName(MockConstants.STRING);
        toReturn.setDescription(MockConstants.STRING);
        toReturn.setMusicians(new ArrayList<>());
        toReturn.setQuoteRequests(new ArrayList<>());

        return toReturn;
    }
}
