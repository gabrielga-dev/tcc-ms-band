package br.com.events.band.newer.data.io.address.city;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CityResponse {

    private Long id;
    private String name;
}
