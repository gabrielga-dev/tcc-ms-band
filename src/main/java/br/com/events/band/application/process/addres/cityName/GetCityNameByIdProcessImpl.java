package br.com.events.band.application.process.addres.cityName;

import br.com.events.band.domain.io.process.address.getCityName.in.GetCityNameByIdProcessRequest;
import br.com.events.band.infrastructure.feign.msLocation.LocationFeignClient;
import br.com.events.band.infrastructure.process.address.cityName.GetCityNameByIdProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This process get the city's name by its id at MS-LOCATION
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class GetCityNameByIdProcessImpl implements GetCityNameByIdProcess {

    private final LocationFeignClient locationFeignClient;

    @Override
    public String execute(GetCityNameByIdProcessRequest param) {
        var response = locationFeignClient.getCityByIdAndStateAndCountryIso(
                param.getCountryIso(), param.getStateIso(), param.getCityId()
        );

        return response.getName();
    }
}
