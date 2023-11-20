package br.com.events.band.data.io.person.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * This class returns the result of the "get authenticated person information" at MS-AUTH
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@ToString
public class PersonResponse {

    private String uuid;
    private String firstName;
    private String lastName;
    private String email;
}
