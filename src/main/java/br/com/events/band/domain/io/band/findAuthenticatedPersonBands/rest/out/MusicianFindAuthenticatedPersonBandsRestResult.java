package br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MusicianFindAuthenticatedPersonBandsRestResult {

    private String uuid;
    private String firstName;
    private String lastName;
    private Integer age;
    private Long creationDateMilliseconds;
    private String avatarUuid;
}
