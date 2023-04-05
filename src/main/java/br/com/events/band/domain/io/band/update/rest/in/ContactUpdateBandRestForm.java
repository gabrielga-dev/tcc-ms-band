package br.com.events.band.domain.io.band.update.rest.in;

import br.com.events.band.domain.entity.type.ContactType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class holds every needed information about the new contact of the band
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class ContactUpdateBandRestForm {

    private String uuid;

    @NotNull(message = "O campo do tipo do contato não pode ser nulo.")
    private ContactType type;

    @NotNull(message = "O campo do link do contato da banda não pode ser nulo.")
    @NotBlank(message = "O campo do link do contato da banda não pode estar vazio.")
    @Size(min = 5, max = 150, message = "O campo do link do contato da banda deve conter, pelo menos, 5 caracteres e no máximo 150.")
    private String content;
}
