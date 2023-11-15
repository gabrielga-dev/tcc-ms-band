package br.com.events.band.older.infrastructure.process.address.cityName;

import br.com.events.band.older.domain.io.process.address.getCityName.in.GetCityNameByIdProcessRequest;
import br.com.events.band.older.infrastructure.process.BaseProcess;

/**
 * This interfaces dictates that what is needed to get a city's name is a {@link GetCityNameByIdProcessRequest} object
 *
 * @author Gabriel Guiamrães de Almeida
 */
public interface GetCityNameByIdProcess extends BaseProcess<GetCityNameByIdProcessRequest, String> {
}
