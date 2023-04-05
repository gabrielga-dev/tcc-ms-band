package br.com.events.band.domain.io.contact.listBandContact.rest.out;

import br.com.events.band.domain.entity.type.ContactType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListBandContactRestResult {

    private String uuid;
    private ContactType type;
    private String content;
}
