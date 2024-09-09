package br.com.events.band.data.model.table.musician;

import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicianAddressTableMock {

    public static MusicianAddressTable build() {
        var toReturn = new MusicianAddressTable();

        toReturn.setStreet(MockConstants.STRING);
        toReturn.setNumber(MockConstants.INTEGER);
        toReturn.setNeighbour(MockConstants.STRING);
        toReturn.setComplement(MockConstants.STRING);
        toReturn.setCity(MockConstants.LONG);
        toReturn.setState(MockConstants.STRING);
        toReturn.setCountry(MockConstants.STRING);
        toReturn.setZipCode(MockConstants.STRING);
        toReturn.setLatitude(MockConstants.BIG_DECIMAL);
        toReturn.setLongitude(MockConstants.BIG_DECIMAL);

        return toReturn;
    }
}
