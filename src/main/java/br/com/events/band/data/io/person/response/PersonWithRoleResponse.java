package br.com.events.band.data.io.person.response;

import br.com.events.band.data.io.role.response.RoleResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


/**
 * This class returns the result of the "get authenticated person information" at MS-AUTH
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@ToString
public class PersonWithRoleResponse extends PersonResponse {

    private List<RoleResponse> roles;
}
