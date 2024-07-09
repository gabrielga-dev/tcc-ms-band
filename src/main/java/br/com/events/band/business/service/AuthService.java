package br.com.events.band.business.service;

public interface AuthService {

    boolean isAuthenticated();
    String getAuthenticatedPersonUuid();
}
