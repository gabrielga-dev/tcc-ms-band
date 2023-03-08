package br.com.events.band.application.process.band.create.validations;

import br.com.events.band.application.process.band.create.exception.CreateBandLocationDoesntExistsException;
import br.com.events.band.domain.io.band.create.useCase.in.CreateBandUseCaseForm;
import br.com.events.band.infrastructure.feign.msLocation.LocationFeignClient;
import br.com.events.band.infrastructure.process.band.create.CreateBandValidation;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class validates if the incoming event's date is on present or future
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class PlaceExistenceCreateBandValidationImpl implements CreateBandValidation {

    private final LocationFeignClient locationFeignClient;

    @Override
    public void validate(final CreateBandUseCaseForm form) {
        try{
            var toValidate = form.getAddress();
            locationFeignClient.validateIfAddressExists(
                    toValidate.getCityId(),
                    toValidate.getStateIso(),
                    toValidate.getCountryIso()
            );
        } catch (FeignException fe){
            throw new CreateBandLocationDoesntExistsException();
        }
    }
}
