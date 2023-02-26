package br.com.events.band.domain.io.band.create.rest.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * This class holds every needed information about the address of the new band
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class AddressCreateBandRestForm {

    @NotNull(message = "O campo do nome da rua não pode ser nulo.")
    @NotBlank(message = "O campo do nome da rua não pode estar vazio.")
    @Size(min = 3, max = 50, message = "O campo do nome da rua deve conter, pelo menos, 3 caracteres e no máximo 50.")
    private String street;

    @NotNull(message = "O campo do nome do bairro não pode ser nulo.")
    @NotBlank(message = "O campo do nome do bairro não pode estar vazio.")
    @Size(min = 3, max = 50, message = "O campo do nome do bairro deve conter, pelo menos, 3 caracteres e no máximo 50.")
    private String neighbour;

    @Size(min = 3, max = 10, message = "O campo do complement deve conter, pelo menos, 1 caracteres e no máximo 10.")
    private String complement;

    @NotNull(message = "O campo do nome da cidade não pode ser nulo.")
    @NotBlank(message = "O campo do nome da cidade não pode estar vazio.")
    @Size(min = 3, max = 50, message = "O campo do nome da cidade deve conter, pelo menos, 3 caracteres e no máximo 50.")
    private String city;

    @NotNull(message = "O campo do nome do estado não pode ser nulo.")
    @NotBlank(message = "O campo do nome do estado não pode estar vazio.")
    @Size(min = 2, max = 2, message = "O campo do nome do estado deve conter 2 caracteres.")
    private String state;

    @NotNull(message = "O campo do nome do país não pode ser nulo.")
    @NotBlank(message = "O campo do nome do país não pode estar vazio.")
    @Size(min = 2, max = 2, message = "O campo do nome do país deve conter 2 caracteres.")
    private String country;

    @NotNull(message = "O campo do CEP não pode ser nulo.")
    @NotBlank(message = "O campo do CEP não pode estar vazio.")
    @Size(min = 5, max = 25, message = "O campo do CEP deve conter, pelo menos, 5 caracteres e no máximo 25.")
    private String zipCode;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
