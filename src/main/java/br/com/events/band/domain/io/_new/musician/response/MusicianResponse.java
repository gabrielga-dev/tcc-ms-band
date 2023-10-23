package br.com.events.band.domain.io._new.musician.response;

import br.com.events.band.domain.entity.Musician;
import br.com.events.band.domain.io._new.address.response.AddressResponse;
import br.com.events.band.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MusicianResponse {

    private final String uuid;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String cpf;

    private final Integer age;
    private final Long creationDateMilliseconds;

    private final String avatarUuid;

    private AddressResponse address;

    public void setAddress(AddressResponse address){
        this.address = address;
    }

    public MusicianResponse(Musician musician) {
        this.uuid = musician.getUuid();
        this.firstName = musician.getFirstName();
        this.lastName = musician.getLastName();
        this.email = musician.getEmail();
        this.cpf = musician.getCpf();

        this.age = DateUtil.calculateAgeByBirthday(musician.getBirthday());
        this.creationDateMilliseconds = DateUtil.localDateTimeToMilliseconds(musician.getCreationDate());

        this.avatarUuid = musician.getAvatarUuid();
    }
}
