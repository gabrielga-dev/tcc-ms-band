package br.com.events.band.domain.io.band.findByUuid.rest.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FindBandByUuidRestResult {

    private String uuid;
    private String name;
    private String description;
    private Boolean active;
    private Long creationDateMilliseconds;
    private List<MusicianFindBandByUuidRestResult> musicians;
    private BandAddressFindBandByUuidRestResult address;
    private List<ContactFindBandByUuidRestResult> contacts;
    private Integer numberOfMusics;
}
