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
    private Long cityId;
    private String stateIso;
    private String countryIso;
}
