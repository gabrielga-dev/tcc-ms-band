package br.com.events.band.data.io.band.request;

import br.com.events.band.data.io.address.request.AddressRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class holds every new information of the band
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class UpdateBandRequest {

    @NotNull(message = "O campo do nome da banda não pode ser nulo.")
    @NotBlank(message = "O campo do nome da banda não pode estar vazio.")
    @Size(min = 1, max = 100, message = "O campo do nome da banda deve conter, pelo menos, 1 caracter e no máximo 100.")
    private String name;

    @NotNull(message = "O campo da descrição da banda não pode ser nulo.")
    @NotBlank(message = "O campo da descrição da banda não pode estar vazio.")
    @Size(min = 5, max = 500, message = "O campo da descrição da banda deve conter, pelo menos, 5 caracteres e no máximo 500.")
    private String description;

    @Valid
    private AddressRequest address;

    private boolean clearProfilePicture;
}
