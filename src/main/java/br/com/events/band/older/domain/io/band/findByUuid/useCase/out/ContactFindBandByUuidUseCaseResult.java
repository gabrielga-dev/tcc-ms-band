package br.com.events.band.older.domain.io.band.findByUuid.useCase.out;

import br.com.events.band.older.domain.entity.type.ContactType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContactFindBandByUuidUseCaseResult {

    private String uuid;
    private ContactType type;
    private String content;
}
