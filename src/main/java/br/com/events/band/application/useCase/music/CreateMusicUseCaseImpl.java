package br.com.events.band.application.useCase.music;

import br.com.events.band.domain.io.music.create.CreateMusicMapper;
import br.com.events.band.domain.io.music.create.in.CreateMusicUseCaseForm;
import br.com.events.band.domain.io.music.create.out.CreateMusicResult;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.domain.repository.MusicRepository;
import br.com.events.band.infrastructure.process.music.create.CreateMusicValidator;
import br.com.events.band.infrastructure.useCase.music.CreateMusicUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateMusicUseCaseImpl implements CreateMusicUseCase {

    private final CreateMusicValidator createMusicValidator;
    private final BandRepository bandRepository;
    private final MusicRepository musicRepository;

    @Override
    public CreateMusicResult execute(CreateMusicUseCaseForm param) {
        createMusicValidator.callProcesses(param);

        var band = bandRepository.findById(param.getBandUuid()).get();
        var toSave = CreateMusicMapper.from(param, band);

        toSave = musicRepository.save(toSave);

        return CreateMusicResult
                .builder()
                .musicUuid(toSave.getUuid())
                .build();
    }
}
