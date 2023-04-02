package br.com.events.band.application.useCase.band;

import br.com.events.band.application.useCase.band.exception.BandNotFoundException;
import br.com.events.band.domain.io.band.update.useCase.in.UpdateBandUseCaseForm;
import br.com.events.band.domain.mapper.band.UpdateBandMapper;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.infrastructure.process.band.update.UpdateBandValidator;
import br.com.events.band.infrastructure.useCase.band.UpdateBandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateBandUseCaseImpl implements UpdateBandUseCase {

    private final BandRepository bandRepository;
    private final UpdateBandValidator updateBandValidator;

    @Override
    public Void execute(UpdateBandUseCaseForm param) {
        var band = bandRepository.findById(param.getUuid())
                .orElseThrow(BandNotFoundException::new);
        var validatorDto = UpdateBandMapper.toValidatorDto(band, param);

        updateBandValidator.callProcesses(validatorDto);

        UpdateBandMapper.transferData(band, param);

        bandRepository.save(band);

        return null;
    }
}
