package br.com.events.band.domain.io.band.findAuthenticatedPersonBands.useCase.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class FindAuthenticatedPersonBandsUseCaseResult {

    private String uuid;
    private String name;
    private String description;
    private Boolean active;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private List<MusicianFindAuthenticatedPersonBandsUseCaseResult> musicians;
    private BandAddressFindAuthenticatedPersonBandsUseCaseResult address;
    private List<ContactFindAuthenticatedPersonBandsUseCaseResult> contacts;
    private Integer numberOfMusics;
}
