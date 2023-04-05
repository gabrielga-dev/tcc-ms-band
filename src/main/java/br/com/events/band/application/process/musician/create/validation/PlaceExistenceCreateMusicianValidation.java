package br.com.events.band.application.process.musician.create.validation;

import br.com.events.band.application.process.exception.LocationDoesntExistsException;
import br.com.events.band.domain.io.musician.create.useCase.in.CreateMusicianUseCaseForm;
import br.com.events.band.infrastructure.feign.msLocation.LocationFeignClient;
import br.com.events.band.infrastructure.process.musician.create.CreateMusicianValidation;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceExistenceCreateMusicianValidation implements CreateMusicianValidation {

    private final LocationFeignClient locationFeignClient;

    @Override
    public void validate(final CreateMusicianUseCaseForm form) {
        try{
            var toValidate = form.getAddress();
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
