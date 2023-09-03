package br.com.events.band.application.useCase.music;

import br.com.events.band.application.process.exception.MusicNonExistenceException;
import br.com.events.band.domain.repository.MusicRepository;
import br.com.events.band.infrastructure.process.music.delete.DeleteMusicValidator;
import br.com.events.band.infrastructure.useCase.music.DeleteMusicUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DeleteMusicUseCaseImpl implements DeleteMusicUseCase {

    private final DeleteMusicValidator validator;
    private final MusicRepository musicRepository;

    @Override
    public Void execute(String musicUuid) {
        validator.callProcesses(musicUuid);

        var music = musicRepository.findById(musicUuid).orElseThrow(MusicNonExistenceException::new);
        music.setActive(false);
        music.setUpdateDate(LocalDateTime.now());
        musicRepository.save(music);

        return null;
    }
}
