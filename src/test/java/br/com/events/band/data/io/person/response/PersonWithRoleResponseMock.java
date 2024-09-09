package br.com.events.band.data.io.person.response;


import br.com.events.band.data.io.role.response.RoleResponseMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonWithRoleResponseMock {

    public static PersonWithRoleResponse build(){
        var toReturn = new PersonWithRoleResponse();
        BeanUtils.copyProperties(PersonResponseMock.build(), toReturn);
        toReturn.setRoles(List.of(RoleResponseMock.build()));
        return toReturn;
    }
}
