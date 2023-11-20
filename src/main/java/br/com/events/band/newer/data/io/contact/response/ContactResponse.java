package br.com.events.band.newer.data.io.contact.response;

import br.com.events.band.newer.data.model.table.ContactTable;
import br.com.events.band.newer.data.model.table.type.ContactType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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
