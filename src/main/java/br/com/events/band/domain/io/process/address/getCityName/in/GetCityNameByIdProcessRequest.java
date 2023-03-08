package br.com.events.band.domain.io.process.address.getCityName.in;

import br.com.events.band.infrastructure.process.address.cityName.GetCityNameByIdProcess;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds all needed data at {@link GetCityNameByIdProcess}
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class GetCityNameByIdProcessRequest {

    private String countryIso;
    private String stateIso;
    private Long cityId;
}
