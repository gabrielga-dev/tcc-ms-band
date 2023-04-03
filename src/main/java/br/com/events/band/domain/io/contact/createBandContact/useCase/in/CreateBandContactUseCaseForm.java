package br.com.events.band.domain.io.contact.createBandContact.useCase.in;

import br.com.events.band.domain.entity.type.ContactType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateBandContactUseCaseForm {

    private String bandUuid;
    private ContactType type;
    private String content;
}
