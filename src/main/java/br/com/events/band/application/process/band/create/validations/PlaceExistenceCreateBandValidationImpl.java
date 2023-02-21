package br.com.events.band.application.process.band.create.validations;

import org.springframework.stereotype.Component;

import br.com.events.band.application.process.band.create.exception.CreateBandLocationDoesntExistsException;
import br.com.events.band.domain.io.band.create.useCase.in.CreateBandUseCaseForm;
import br.com.events.band.infrastructure.process.band.create.CreateBandValidation;
import br.com.events.band.infrastructure.service.LocationService;
import lombok.RequiredArgsConstructor;

/**
 * This class validates if the incoming event's date is on present or future
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class PlaceExistenceCreateBandValidationImpl implements CreateBandValidation {

    private final LocationService locationService;

    @Override
    public void validate(final CreateBandUseCaseForm form) {
        var toValidate = form.getAddress();
        locationService.getCityByStateAndCountryIso2(
            toValidate.getCountry(),
            toValidate.getState(),
            toValidate.getCity()
        ).orElseThrow(CreateBandLocationDoesntExistsException::new);
    }
}
