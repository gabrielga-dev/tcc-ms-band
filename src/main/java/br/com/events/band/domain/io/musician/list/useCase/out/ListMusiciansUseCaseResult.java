package br.com.events.band.domain.io.musician.list.useCase.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListMusiciansUseCaseResult {

    private String uuid;
    private String firstName;
    private String lastName;
    private Integer age;
    private Long creationDateMilliseconds;
    private String avatarUuid;
}

