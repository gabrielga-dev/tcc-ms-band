package br.com.events.band.application.useCase.band;

import org.springframework.stereotype.Component;

import br.com.events.band.domain.io.band.create.useCase.in.CreateBandUseCaseForm;
import br.com.events.band.domain.io.band.create.useCase.out.CreateBandUseCaseResult;
import br.com.events.band.domain.mapper.band.CreateBandMapper;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.infrastructure.process.band.create.CreateBandValidator;
import br.com.events.band.infrastructure.useCase.band.CreateBandUseCase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateBandUseCaseImpl implements CreateBandUseCase {

    private final BandRepository bandRepository;
    private final CreateBandValidator createBandValidator;

    @Override
    public CreateBandUseCaseResult execute(final CreateBandUseCaseForm param) {
        createBandValidator.callProcesses(param);

        var entityToSave = CreateBandMapper.toEntity(param);

        var saved = bandRepository.save(entityToSave);

        //todo enviar uuid da banda para o ms auth para ele persistir quais bandas pertencem à pessoa

        return CreateBandMapper.toResult(saved);
    }
}
