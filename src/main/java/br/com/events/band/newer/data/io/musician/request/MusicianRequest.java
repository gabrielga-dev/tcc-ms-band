package br.com.events.band.newer.data.io.musician.request;

import br.com.events.band.newer.data.io.address.request.AddressRequest;
import br.com.events.band.newer.core.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicianRequest {

    @NotNull(message = "O campo do primeiro nome não pode ser nulo.")
    @NotBlank(message = "O campo do primeiro nome não pode estar vazio.")
    @Size(min = 3, max = 75, message = "O campo do primeiro nome deve conter, pelo menos, 3 caracter e no máximo 75.")
    private String firstName;

    @NotNull(message = "O campo do sobrenome não pode ser nulo.")
    @NotBlank(message = "O campo do sobrenome não pode estar vazio.")
    @Size(min = 1, max = 150, message = "O campo do sobrenome deve conter, pelo menos, 1 caracter e no máximo 150.")
    private String lastName;

    @NotNull(message = "O campo de aniversário não pode ser nulo.")
    private Long birthday;

    @CPF(message = "Deve ser inserido um CPF válido!")
    @NotNull(message = "O campo do CPF não pode ser nulo.")
    @NotBlank(message = "O campo do CPF não pode estar vazio.")
    @Size(min = 14, max = 14, message = "O campo do CPF deve conter 14 caracteres.")
    private String cpf;

    @Email(message = "Deve ser inserido um email válido!")
    @NotNull(message = "O campo do email não pode ser nulo.")
    @NotBlank(message = "O campo do email não pode estar vazio.")
    @Size(min = 5, max = 100, message = "O campo do email deve conter, pelo menos, 5 caracter e no máximo 100.")
    private String email;

    @Valid
    @NotNull(message = "É necessário inserir um endereço.")
    private AddressRequest address;

    public LocalDateTime getBirthday(){
        return DateUtil.millisecondsToLocalDateTime(this.birthday);
    }

    public int getAge() {
        return DateUtil.calculateAgeByBirthday(this.getBirthday());
    }
}
