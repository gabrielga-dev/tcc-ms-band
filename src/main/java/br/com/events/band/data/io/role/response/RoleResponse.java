package br.com.events.band.data.io.role.response;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Getter
@ToString
public class RoleResponse implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
