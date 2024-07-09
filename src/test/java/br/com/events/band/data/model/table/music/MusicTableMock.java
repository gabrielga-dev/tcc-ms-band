package br.com.events.band.data.model.table.music;

import br.com.events.band.MockConstants;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicTableMock {

    public static MusicTable build() {
        var toReturn = new MusicTable();

        toReturn.setContributingBand(new BandTable());
        toReturn.setName(MockConstants.STRING);
        toReturn.setAuthor(MockConstants.STRING);
        toReturn.setArtist(MockConstants.STRING);
        toReturn.setObservation(MockConstants.STRING);
        toReturn.setCreationDate(MockConstants.LOCAL_DATE_TIME);
        toReturn.setUpdateDate(MockConstants.LOCAL_DATE_TIME);

        return toReturn;
    }
}
