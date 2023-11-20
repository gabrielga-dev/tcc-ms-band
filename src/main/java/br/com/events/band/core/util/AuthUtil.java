package br.com.events.band.core.util;

import br.com.events.band.data.io.auth.AuthenticatedPerson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthUtil {

    public static String getAuthenticatedPersonUuid() {
        return getAuthenticatedPerson().getUuid();
    }

    public static AuthenticatedPerson getAuthenticatedPerson() {
        return ((AuthenticatedPerson) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public static boolean isAuthenticated() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .noneMatch(role -> "ROLE_ANONYMOUS".equals(role.getAuthority()));
    }
}
