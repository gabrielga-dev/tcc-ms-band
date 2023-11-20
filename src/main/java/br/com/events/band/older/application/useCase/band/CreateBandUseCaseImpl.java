package br.com.events.band.older.application.useCase.band;

import br.com.events.band.newer.data.model.table.BandTable;
import br.com.events.band.older.domain.io.UuidHolderDTO;
import br.com.events.band.older.domain.io._new.band.dto.BandValidationDto;
import br.com.events.band.newer.data.io.band.request.BandRequest;
import br.com.events.band.newer.data.io.band.ServiceType;
import br.com.events.band.older.domain.repository.BandRepository;
import br.com.events.band.older.domain.type.MethodValidationType;
import br.com.events.band.older.infrastructure.feign.msAuth.PersonMsAuthFeignClient;
import br.com.events.band.older.infrastructure.process.band.BandMethodValidator;
import br.com.events.band.older.infrastructure.useCase.band.CreateBandUseCase;
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
    public UuidHolderDTO execute(final BandRequest form) {
        var validationDto = new BandValidationDto(form, MethodValidationType.CREATE);
        bandMethodValidator.callProcesses(validationDto);

        var entityToSave = new BandTable(form);

        var saved = bandRepository.save(entityToSave);

        personMsAuthFeignClient.addServiceToPerson(saved.getUuid(), ServiceType.BAND);

        return new UuidHolderDTO(saved.getUuid());
    }
}
