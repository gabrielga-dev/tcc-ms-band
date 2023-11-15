package br.com.events.band.older.application.process.band.update.validations;

import br.com.events.band.newer.core.exception.address.LocationDoesntExistsException;
import br.com.events.band.older.domain.io.process.band.update.UpdateBandProcessDTO;
import br.com.events.band.newer.adapter.feign.client.MsLocationFeignClient;
import br.com.events.band.older.infrastructure.process.band.update.UpdateBandValidation;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * This class validates if the incoming event's date is on present or future
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class PlaceExistenceUpdateBandValidationImpl implements UpdateBandValidation {

    private final MsLocationFeignClient locationFeignClient;

    @Override
    public boolean matches(UpdateBandProcessDTO param) {
        var newAddress = param.getUseCaseForm().getAddress();
        var oldAddress = param.getEntity().getAddress();
        return (Objects.equals(oldAddress.getCity(), newAddress.getCityId()))
                || (Objects.equals(oldAddress.getState(), newAddress.getStateIso()))
                || (Objects.equals(oldAddress.getCountry(), newAddress.getCountryIso()));
    }

    @Override
    public void validate(final UpdateBandProcessDTO form) {

        try{
            var toValidate = form.getUseCaseForm().getAddress();
            locationFeignClient.validateIfAddressExists(
                    toValidate.getCityId(),
                    toValidate.getStateIso(),
                    toValidate.getCountryIso()
            );
        } catch (FeignException fe){
            throw new LocationDoesntExistsException();
        }
    }
}
