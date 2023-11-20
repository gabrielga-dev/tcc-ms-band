package br.com.events.band.newer.data.io.auth;

import br.com.events.band.newer.data.io.person.response.PersonResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * This class holds all needed data of the current authenticated person
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
public class AuthenticatedPerson implements UserDetails {

    private String uuid;
    private String firstName;
    private String lastName;
    private String email;

    public AuthenticatedPerson(PersonResponse person) {
        this.uuid = person.getUuid();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
