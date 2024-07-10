package br.com.events.band.data.io.auth;

import br.com.events.band.data.io.person.response.PersonWithRoleResponseMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthenticatedPersonMock {

    public static AuthenticatedPerson build() {
        return new AuthenticatedPerson(PersonWithRoleResponseMock.build());
    }
}
