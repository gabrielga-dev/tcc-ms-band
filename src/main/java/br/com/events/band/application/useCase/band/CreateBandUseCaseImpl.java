package br.com.events.band.application.useCase.band;

import org.springframework.stereotype.Component;

import br.com.events.band.domain.io.band.create.useCase.in.CreateBandUseCaseForm;
import br.com.events.band.domain.io.band.create.useCase.out.CreateBandUseCaseResult;
import br.com.events.band.domain.io.feign.msAuth.person.addServiceToPerson.AddServiceToPersonServiceType;
import br.com.events.band.domain.mapper.band.CreateBandMapper;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.infrastructure.feign.msAuth.PersonMsAuthFeignClient;
import br.com.events.band.infrastructure.process.band.create.CreateBandValidator;
import br.com.events.band.infrastructure.useCase.band.CreateBandUseCase;
import lombok.RequiredArgsConstructor;

/**
 * This class implements the {@link CreateBandUseCase} interface and creates a new band with the incoming information
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreateBandUseCaseImpl implements CreateBandUseCase {

    private final BandRepository bandRepository;
    private final CreateBandValidator createBandValidator;
    private final PersonMsAuthFeignClient personMsAuthFeignClient;

    @Override
    public CreateBandUseCaseResult execute(final CreateBandUseCaseForm param) {
        createBandValidator.callProcesses(param);

        var entityToSave = CreateBandMapper.toEntity(param);

        var saved = bandRepository.save(entityToSave);

        personMsAuthFeignClient.addServiceToPerson(saved.getUuid(), AddServiceToPersonServiceType.BAND);

        return CreateBandMapper.toResult(saved);
    }
}
