package br.com.events.band.data.model.table.band;


import br.com.events.band.MockConstants;
import br.com.events.band.data.model.table.musician.MusicianTableMock;
import br.com.events.band.data.model.table.pk.BandMusicianTablePk;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BandMusicianTableMock {

    public static BandMusicianTable build() {
        var toReturn = new BandMusicianTable();

        toReturn.setPk(new BandMusicianTablePk());
        toReturn.getPk().setBandUuid(MockConstants.STRING);
        toReturn.getPk().setMusicianUuid(MockConstants.STRING);

        toReturn.setBand(new BandTable());
        toReturn.setMusician(MusicianTableMock.build());
        toReturn.setCreationDate(MockConstants.LOCAL_DATE_TIME);

        return toReturn;
    }
}
