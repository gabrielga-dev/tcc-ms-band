package br.com.events.band.older.domain.io.musician.create.useCase.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CreateMusicianUseCaseForm {

    private String bandUuid;

    private String firstName;
    private String lastName;
    private LocalDateTime birthday;
    private String cpf;
    private String email;
    private AddressCreateMusicianUseCaseForm address;
}
