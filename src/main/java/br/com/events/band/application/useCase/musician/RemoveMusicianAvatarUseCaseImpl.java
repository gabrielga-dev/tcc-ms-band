package br.com.events.band.application.useCase.musician;

import br.com.events.band.application.process.exception.MusicianDoesNotExistException;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.removeAvatar.RemoveMusicianAvatarValidationCaller;
import br.com.events.band.infrastructure.useCase.musician.RemoveMusicianAvatarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RemoveMusicianAvatarUseCaseImpl implements RemoveMusicianAvatarUseCase {

        private final RemoveMusicianAvatarValidationCaller removeMusicianAvatarValidationCaller;
        private final MusicianRepository musicianRepository;

    @Override
    public Void execute(String param) {
        removeMusicianAvatarValidationCaller.callProcesses(param);

        var musician = musicianRepository.findById(param).orElseThrow(MusicianDoesNotExistException::new);

        musician.setAvatarUuid(null);
        musician.setUpdateDate(LocalDateTime.now());

        musicianRepository.save(musician);

        return null;
    }
}
