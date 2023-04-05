package br.com.events.band.domain.io.musician.list.rest.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListMusiciansRestResult {

    private String uuid;
    private String firstName;
    private String lastName;
    private Integer age;
    private Long creationDateMilliseconds;
}

