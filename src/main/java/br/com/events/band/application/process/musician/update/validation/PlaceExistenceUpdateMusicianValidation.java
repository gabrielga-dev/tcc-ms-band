package br.com.events.band.application.process.musician.update.validation;

import br.com.events.band.application.process.exception.LocationDoesntExistsException;
import br.com.events.band.domain.io.musician.update.useCase.in.UpdateMusicianUseCaseForm;
import br.com.events.band.infrastructure.feign.msLocation.LocationFeignClient;
import br.com.events.band.infrastructure.process.musician.update.UpdateMusicianValidation;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceExistenceUpdateMusicianValidation implements UpdateMusicianValidation {

    private final LocationFeignClient locationFeignClient;

    @Override
    public void validate(final UpdateMusicianUseCaseForm form) {
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
