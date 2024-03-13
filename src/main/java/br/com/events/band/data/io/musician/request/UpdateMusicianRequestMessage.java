package br.com.events.band.data.io.musician.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateMusicianRequestMessage {

    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private String personUuid;
}
