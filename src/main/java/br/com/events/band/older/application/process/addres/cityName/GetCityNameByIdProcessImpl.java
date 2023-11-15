package br.com.events.band.older.application.process.addres.cityName;

import br.com.events.band.older.domain.io.process.address.getCityName.in.GetCityNameByIdProcessRequest;
import br.com.events.band.newer.adapter.feign.client.MsLocationFeignClient;
import br.com.events.band.older.infrastructure.process.address.cityName.GetCityNameByIdProcess;
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

    private final MsLocationFeignClient locationFeignClient;

    @Override
    public String execute(GetCityNameByIdProcessRequest param) {
        var response = locationFeignClient.getCityByIdAndStateAndCountryIso(
                param.getCountryIso(), param.getStateIso(), param.getCityId()
        );

        return response.getName();
    }
}
