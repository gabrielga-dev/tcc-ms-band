package br.com.events.band.data.model.table.band.contact;

import br.com.events.band.MockConstants;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContactTableMock {

    public static ContactTable build(){
        return new ContactTable(
                MockConstants.STRING,
                new BandTable(),
                ContactType.OTHER,
                MockConstants.STRING,
                MockConstants.LOCAL_DATE_TIME
        );
    }
}
