package br.com.events.band.older.application.process.musician.validations;

import br.com.events.band.newer.core.exception.address.LocationDoesntExistsException;
import br.com.events.band.older.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.newer.adapter.feign.client.MsLocationFeignClient;
import br.com.events.band.older.infrastructure.process.musician.MusicianMethodValidation;
import br.com.events.band.older.domain.type.MethodValidationType;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaceExistenceMusicianValidation implements MusicianMethodValidation {

    private final MsLocationFeignClient locationFeignClient;

    @Override
    public boolean matches(MusicianValidationDto dto) {
        return List.of(MethodValidationType.CREATE, MethodValidationType.EDIT).contains(dto.getMethodType());
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
