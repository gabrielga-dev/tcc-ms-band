package br.com.events.band.older.application.useCase.music;

import br.com.events.band.newer.core.exception.music.MusicNonExistenceException;
import br.com.events.band.newer.data.table.MusicTable;
import br.com.events.band.older.domain.io.music.create.out.CreateMusicResult;
import br.com.events.band.older.domain.io.music.update.in.UpdateMusicUseCaseForm;
import br.com.events.band.older.domain.repository.MusicRepository;
import br.com.events.band.older.infrastructure.process.music.update.UpdateMusicValidator;
import br.com.events.band.older.infrastructure.useCase.music.UpdateMusicUseCase;
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

    private void setFields(MusicTable music, UpdateMusicUseCaseForm param) {
        music.setName(param.getName());
        music.setObservation(param.getObservation());
    }
}
