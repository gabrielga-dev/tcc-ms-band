package br.com.events.band.data.io.role.response;


import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.test.util.ReflectionTestUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final  class RoleResponseMock {

    public static RoleResponse build(){
        var toReturn = new RoleResponse();
        ReflectionTestUtils.setField(toReturn, "name", MockConstants.STRING);
        return toReturn;
    }
}
