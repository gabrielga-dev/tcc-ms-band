package br.com.events.band.data.io.auth;

import br.com.events.band.data.io.person.response.PersonWithRoleResponse;
import br.com.events.band.data.io.role.response.RoleResponse;
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
    private String cpf;
    private List<RoleResponse> roles;

    public AuthenticatedPerson(PersonWithRoleResponse person) {
        this.uuid = person.getUuid();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        this.cpf = person.getCpf();
        this.roles = person.getRoles();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
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
