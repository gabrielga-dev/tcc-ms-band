package br.com.events.band.application.useCase.musician;

import br.com.events.band.application.process.musician.exception.MusicianNotPresentOnBandException;
import br.com.events.band.domain.io.musician.update.useCase.in.UpdateMusicianUseCaseForm;
import br.com.events.band.domain.mapper.musician.UpdateMusicianMapper;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.update.UpdateMusicianValidator;
import br.com.events.band.infrastructure.useCase.musician.UpdateMusicianUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateMusicianUseCaseImpl implements UpdateMusicianUseCase {

    private final MusicianRepository musicianRepository;
    private final UpdateMusicianValidator updateMusicianValidator;

    @Override
    public Void execute(UpdateMusicianUseCaseForm param) {
        updateMusicianValidator.callProcesses(param);

        var musician = musicianRepository.findById(param.getMusicianUuid())
                .orElseThrow(MusicianNotPresentOnBandException::new);

        UpdateMusicianMapper.transferData(musician, param);

        musicianRepository.save(musician);
        return null;
    }
}
