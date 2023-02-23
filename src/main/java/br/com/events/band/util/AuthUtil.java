package br.com.events.band.util;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.events.band.domain.io.auth.AuthenticatedPerson;

/**
 * This class is used when is needed to reclaim the authenticated person's data
 *
 * @author Gabriel Guimarães de Almeida
 */
public final class AuthUtil {

    /**
     * This method returns the authenticated user
     *
     * @return {@link AuthenticatedPerson} object that contains the authenticated person's information
     */
    public static AuthenticatedPerson getAuthenticatedPerson() {
        return (AuthenticatedPerson) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
