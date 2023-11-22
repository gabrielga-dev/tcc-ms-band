package br.com.events.band.data.io.contact.response;

import br.com.events.band.data.model.table.band.contact.ContactTable;
import br.com.events.band.data.model.table.band.contact.ContactType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactResponse {

    private String uuid;
    private ContactType type;
    private String content;

    public ContactResponse(ContactTable contactTable) {
        this.uuid = contactTable.getUuid();
        this.type = contactTable.getType();
        this.content = contactTable.getContent();
    }
}
