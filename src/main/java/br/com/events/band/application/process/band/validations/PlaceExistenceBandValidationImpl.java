package br.com.events.band.application.process.band.validations;

import br.com.events.band.application.process.exception.LocationDoesntExistsException;
import br.com.events.band.domain.io._new.band.dto.BandValidationDto;
import br.com.events.band.infrastructure.feign.msLocation.LocationFeignClient;
import br.com.events.band.infrastructure.process.band.BandMethodValidation;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.events.band.domain.type.MethodValidationType.CREATE;
import static br.com.events.band.domain.type.MethodValidationType.EDIT;

/**
 * This class validates if the incoming event's date is on present or future
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class PlaceExistenceBandValidationImpl implements BandMethodValidation {

    private final LocationFeignClient locationFeignClient;

    @Override
    public boolean matches(BandValidationDto dto) {
        return List.of(CREATE, EDIT).contains(dto.getMethodType());
    }

    @Override
    public void validate(final BandValidationDto dto) {
        try {
            var form = dto.getForm();
            var toValidate = form.getAddress();
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
