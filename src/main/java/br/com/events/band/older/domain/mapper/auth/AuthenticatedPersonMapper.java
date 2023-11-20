package br.com.events.band.older.domain.mapper.auth;

import br.com.events.band.older.domain.io.auth.AuthenticatedPerson;
import br.com.events.band.newer.data.io.person.response.PersonResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class map every needed class at authentication needs
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthenticatedPersonMapper {

    /**
     * This method maps a {@link PersonResponse} into a {@link AuthenticatedPerson} object
     *
     * @param result {@link PersonResponse} object with the data to be mapped
     * @return {@link AuthenticatedPerson} object with the mapped information
     */
    public static AuthenticatedPerson convertToAuthenticatedPerson(PersonResponse result) {
        return AuthenticatedPerson
            .builder()
            .uuid(result.getUuid())
            .firstName(result.getFirstName())
            .lastName(result.getLastName())
            .email(result.getEmail())
            .build();
    }
}
