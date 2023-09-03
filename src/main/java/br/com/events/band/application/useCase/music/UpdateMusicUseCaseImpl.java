package br.com.events.band.application.useCase.music;

import br.com.events.band.application.process.exception.MusicNonExistenceException;
import br.com.events.band.domain.entity.Music;
import br.com.events.band.domain.io.music.create.out.CreateMusicResult;
import br.com.events.band.domain.io.music.update.in.UpdateMusicUseCaseForm;
import br.com.events.band.domain.repository.MusicRepository;
import br.com.events.band.infrastructure.process.music.update.UpdateMusicValidator;
import br.com.events.band.infrastructure.useCase.music.UpdateMusicUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateMusicUseCaseImpl implements UpdateMusicUseCase {

    private final UpdateMusicValidator validator;
    private final MusicRepository musicRepository;

    @Override
    public CreateMusicResult execute(UpdateMusicUseCaseForm param) {
        validator.validate(param);

        var music = musicRepository.findById(param.getMusicUuid())
                .orElseThrow(MusicNonExistenceException::new);

        this.setFields(music, param);

        music = musicRepository.save(music);

        return new CreateMusicResult(music.getUuid());
    }

    private void setFields(Music music, UpdateMusicUseCaseForm param) {
        music.setName(param.getName());
        music.setObservation(param.getObservation());
    }
}
