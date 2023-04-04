package br.com.events.band.domain.io.contact.updateBandContact.useCase.in;

import br.com.events.band.domain.entity.type.ContactType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateBandContactUseCaseForm {

    private String bandUuid;
    private String contactUuid;
    private ContactType type;
    private String content;
}
