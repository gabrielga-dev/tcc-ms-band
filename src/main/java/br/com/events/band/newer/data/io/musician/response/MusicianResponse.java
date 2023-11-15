package br.com.events.band.newer.data.io.musician.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MusicianResponse {

    private String uuid;
    private String firstName;
    private String lastName;
    private Integer age;
    private Long creationDateMilliseconds;
    private String avatarUuid;
}
