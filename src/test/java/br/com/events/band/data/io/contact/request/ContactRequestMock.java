package br.com.events.band.data.io.contact.request;

import br.com.events.band.MockConstants;
import br.com.events.band.data.model.table.band.contact.ContactType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContactRequestMock {

    public static ContactRequest build(){
        return new ContactRequest(ContactType.OTHER, MockConstants.STRING);
    }
}
