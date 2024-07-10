package br.com.events.band.business.service;

import br.com.events.band.data.io.auth.AuthenticatedPerson;

public interface AuthService {

    boolean isAuthenticated();
    String getAuthenticatedPersonUuid();
    AuthenticatedPerson getAuthenticatedPerson();
}
