package br.com.events.band.domain.io.band.create.useCase.in;

import br.com.events.band.domain.entity.type.ContactType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed information about the contact of the new band at the use case layer
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class ContactCreateBandUseCaseForm {

    private ContactType type;
    private String content;
}
