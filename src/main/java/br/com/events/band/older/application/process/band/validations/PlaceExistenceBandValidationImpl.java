package br.com.events.band.older.application.process.band.validations;

import br.com.events.band.newer.core.exception.address.LocationDoesntExistsException;
import br.com.events.band.older.domain.io._new.band.dto.BandValidationDto;
import br.com.events.band.newer.adapter.feign.client.MsLocationFeignClient;
import br.com.events.band.older.infrastructure.process.band.BandMethodValidation;
import br.com.events.band.older.domain.type.MethodValidationType;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class validates if the incoming event's date is on present or future
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class PlaceExistenceBandValidationImpl implements BandMethodValidation {

    private final MsLocationFeignClient locationFeignClient;

    @Override
    public boolean matches(BandValidationDto dto) {
        return List.of(MethodValidationType.CREATE, MethodValidationType.EDIT).contains(dto.getMethodType());
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
