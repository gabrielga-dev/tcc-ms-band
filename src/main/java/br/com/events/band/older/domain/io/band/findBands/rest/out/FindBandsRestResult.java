package br.com.events.band.older.domain.io.band.findBands.rest.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindBandsRestResult {

    private String uuid;
    private String name;
    private String description;
    private String profilePictureUuid;
    private BandAddressFindBandsRestResult address;
    private Integer numberOfMusicians;
    private Integer numberOfMusics;
}
