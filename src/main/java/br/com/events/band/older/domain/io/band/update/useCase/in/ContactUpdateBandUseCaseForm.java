package br.com.events.band.older.domain.io.band.update.useCase.in;

import br.com.events.band.older.domain.entity.type.ContactType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class holds every new information about the contact of the band at the use case layer
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactUpdateBandUseCaseForm {

    private String uuid;
    private ContactType type;
    private String content;
}
