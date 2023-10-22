package br.com.events.band.domain.io.band.findByUuid.rest.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MusicianFindBandByUuidRestResult {

    private String uuid;
    private String firstName;
    private String lastName;
    private String avatarUuid;
    private Integer age;
    private Long creationDateMilliseconds;
}
