package br.com.events.band.application.useCase.band;

import br.com.events.band.domain.entity.Band;
import br.com.events.band.domain.io.UuidHolderDTO;
import br.com.events.band.domain.io._new.band.dto.BandValidationDto;
import br.com.events.band.domain.io._new.band.form.BandForm;
import br.com.events.band.domain.io.feign.msAuth.person.addServiceToPerson.AddServiceToPersonServiceType;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.domain.type.MethodValidationType;
import br.com.events.band.infrastructure.feign.msAuth.PersonMsAuthFeignClient;
import br.com.events.band.infrastructure.process.band.BandMethodValidator;
import br.com.events.band.infrastructure.useCase.band.CreateBandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the {@link CreateBandUseCase} interface and creates a new band with the incoming information
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreateBandUseCaseImpl implements CreateBandUseCase {

    private final BandRepository bandRepository;
    private final BandMethodValidator bandMethodValidator;
    private final PersonMsAuthFeignClient personMsAuthFeignClient;

    @Override
    @Transactional
    public UuidHolderDTO execute(final BandForm form) {
        var validationDto = new BandValidationDto(form, MethodValidationType.CREATE);
        bandMethodValidator.callProcesses(validationDto);

        var entityToSave = new Band(form);

        var saved = bandRepository.save(entityToSave);

        personMsAuthFeignClient.addServiceToPerson(saved.getUuid(), AddServiceToPersonServiceType.BAND);

        return new UuidHolderDTO(saved.getUuid());
    }
}
