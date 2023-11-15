package br.com.events.band.older.domain.io.band.findByUuid.useCase.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class FindBandByUuidUseCaseResult {

    private String uuid;
    private String name;
    private String description;
    private Boolean active;
    private LocalDateTime creationDate;
    private String profilePictureUuid;
    private String ownerUuid;
    private List<MusicianFindBandByUuidUseCaseResult> musicians;
    private BandAddressFindBandByUuidUseCaseResult address;
    private List<ContactFindBandByUuidUseCaseResult> contacts;
    private Integer numberOfMusics;
}
