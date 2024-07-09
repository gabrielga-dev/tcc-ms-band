package br.com.events.band.data.model.table.band;


import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BandAddressTableMock {

    public static BandAddressTable build() {
        var toReturn = new BandAddressTable();

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
