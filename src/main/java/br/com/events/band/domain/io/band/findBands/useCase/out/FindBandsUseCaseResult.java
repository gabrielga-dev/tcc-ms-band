package br.com.events.band.domain.io.band.findBands.useCase.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindBandsUseCaseResult {

    private String uuid;
    private String name;
    private String description;
    private String profilePictureUuid;
    private BandAddressFindBandsUseCaseResult address;
    private Integer numberOfMusicians;
    private Integer numberOfMusics;
}
