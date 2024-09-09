package br.com.events.band.data.model.table.musician;


import br.com.events.band.MockConstants;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicianTableMock {

    public static MusicianTable build() {
        var toReturn = new MusicianTable();

        toReturn.setFirstName(MockConstants.STRING);
        toReturn.setLastName(MockConstants.STRING);
        toReturn.setBirthday(MockConstants.LOCAL_DATE_TIME);
        toReturn.setCpf(MockConstants.STRING);
        toReturn.setEmail(MockConstants.STRING);
        toReturn.setBandThatInserted(new BandTable());
        toReturn.setBandsAssociated(new ArrayList<>());
        toReturn.setCreationDate(MockConstants.LOCAL_DATE_TIME);
        toReturn.setUpdateDate(MockConstants.LOCAL_DATE_TIME);
        toReturn.setProfilePictureUuid(MockConstants.STRING);
        toReturn.setPersonUuid(MockConstants.STRING);
        toReturn.setAddress(MusicianAddressTableMock.build());
        toReturn.setTypes(List.of(MusicianTypeTableMock.build()));

        return toReturn;
    }
}
