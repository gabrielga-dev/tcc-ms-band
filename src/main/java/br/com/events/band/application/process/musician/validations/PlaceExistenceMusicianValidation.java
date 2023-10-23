package br.com.events.band.application.process.musician.validations;

import br.com.events.band.application.process.exception.LocationDoesntExistsException;
import br.com.events.band.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.infrastructure.feign.msLocation.LocationFeignClient;
import br.com.events.band.infrastructure.process.musician.MusicianMethodValidation;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.events.band.domain.type.MethodValidationType.CREATE;
import static br.com.events.band.domain.type.MethodValidationType.EDIT;

@Component
@RequiredArgsConstructor
public class PlaceExistenceMusicianValidation implements MusicianMethodValidation {

    private final LocationFeignClient locationFeignClient;

    @Override
    public boolean matches(MusicianValidationDto dto) {
        return List.of(CREATE, EDIT).contains(dto.getMethodType());
    }

    @Override
    public void validate(final MusicianValidationDto form) {
        try {
            var toValidate = form.getForm().getAddress();
            locationFeignClient.validateIfAddressExists(
                    toValidate.getCityId(),
                    toValidate.getStateIso(),
                    toValidate.getCountryIso()
            );
        } catch (FeignException fe) {
            throw new LocationDoesntExistsException();
        }
    }
}
