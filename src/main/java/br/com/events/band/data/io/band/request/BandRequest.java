package br.com.events.band.data.io.band.request;

import br.com.events.band.data.io.address.request.AddressRequest;
import br.com.events.band.data.io.band.IBandRequest;
import br.com.events.band.data.io.contact.request.ContactRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * This class holds every needed information about the new band
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class BandRequest implements IBandRequest {

    @NotNull(message = "O campo do nome da banda não pode ser nulo.")
    @NotBlank(message = "O campo do nome da banda não pode estar vazio.")
    @Size(min = 1, max = 100, message = "O campo do nome da banda deve conter, pelo menos, 1 caracter e no máximo 100.")
    protected String name;

    @NotNull(message = "O campo da descrição da banda não pode ser nulo.")
    @NotBlank(message = "O campo da descrição da banda não pode estar vazio.")
    @Size(min = 5, max = 500, message = "O campo da descrição da banda deve conter, pelo menos, 5 caracteres e no máximo 500.")
    protected String description;

    @Valid
    protected AddressRequest address;

    @Valid
    @Builder.Default
    private List<ContactRequest> contacts = new ArrayList<>();
}
