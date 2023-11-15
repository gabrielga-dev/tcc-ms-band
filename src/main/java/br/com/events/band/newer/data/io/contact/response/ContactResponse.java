package br.com.events.band.newer.data.io.contact.response;

import br.com.events.band.newer.data.table.ContactTable;
import br.com.events.band.older.domain.entity.type.ContactType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContactResponse {

    private ContactType type;
    private String content;

    public ContactResponse(ContactTable contactTable) {
        this.type = contactTable.getType();
        this.content = contactTable.getContent();
    }
}
