package br.com.events.band.domain.io.band.findAuthenticatedPersonBands.rest.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FindAuthenticatedPersonBandsRestResult {

    private String uuid;
    private String name;
    private String description;
    private Boolean active;
    private Long creationDateMilliseconds;
    private Long updateDateMilliseconds;
    private List<MusicianFindAuthenticatedPersonBandsRestResult> musicians;
    private BandAddressFindAuthenticatedPersonBandsRestResult address;
    private List<ContactFindAuthenticatedPersonBandsRestResult> contacts;
    private Integer numberOfMusics;
}
