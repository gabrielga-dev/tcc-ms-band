package br.com.events.band.older.domain.io.musician.update.useCase.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UpdateMusicianUseCaseForm {

    private String bandUuid;
    private String musicianUuid;

    private String firstName;
    private String lastName;
    private LocalDateTime birthday;
    private String cpf;
    private String email;
    private AddressUpdateMusicianUseCaseForm address;
}
