package br.com.events.band.data.model.table.band;

import br.com.events.band.MockConstants;
import br.com.events.band.data.model.table.band.contact.ContactTableMock;
import br.com.events.band.data.model.table.musician.MusicianTableMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BandTableMock {

    public static BandTable build() {
        var band = new BandTable();

        band.setUuid(UUID.randomUUID().toString());
        band.setProfilePictureUuid(MockConstants.STRING);
        band.setOwnerUuid(MockConstants.STRING);
        band.setAssociatedMusicians(new ArrayList<>(List.of(BandMusicianTableMock.build())));
        band.setInsertedMusicians(new ArrayList<>(List.of(MusicianTableMock.build())));
        band.setAddress(BandAddressTableMock.build());
        band.setContacts(new ArrayList<>(List.of(ContactTableMock.build())));

        return band;
    }
}
