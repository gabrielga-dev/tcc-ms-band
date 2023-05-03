package br.com.events.band.domain.io.band.findAuthenticatedPersonBands.useCase.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MusicianFindAuthenticatedPersonBandsUseCaseResult {

    private String uuid;
    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDateTime creationDate;
    private String avatarUuid;
}
