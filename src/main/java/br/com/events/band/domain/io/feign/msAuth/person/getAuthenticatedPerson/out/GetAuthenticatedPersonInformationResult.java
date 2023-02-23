package br.com.events.band.domain.io.feign.msAuth.person.getAuthenticatedPerson.out;

import lombok.Builder;
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
@Builder
@ToString
public class GetAuthenticatedPersonInformationResult {

    private String uuid;
    private String firstName;
    private String lastName;
    private String email;
}
