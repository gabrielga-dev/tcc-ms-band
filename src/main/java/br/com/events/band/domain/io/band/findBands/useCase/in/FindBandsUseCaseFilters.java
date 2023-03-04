package br.com.events.band.domain.io.band.findBands.useCase.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@Builder
public class FindBandsUseCaseFilters {

    private Pageable pageable;
    private String name;
    private Double latitude;
    private Double longitude;
    private Double distance;
    private String city;
    private String state;
    private String country;
}
