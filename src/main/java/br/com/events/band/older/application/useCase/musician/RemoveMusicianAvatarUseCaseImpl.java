package br.com.events.band.older.application.useCase.musician;

import br.com.events.band.newer.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.older.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.older.domain.repository.MusicianRepository;
import br.com.events.band.older.domain.type.MethodValidationType;
import br.com.events.band.older.infrastructure.process.musician.MusicianMethodValidator;
import br.com.events.band.older.infrastructure.useCase.musician.RemoveMusicianAvatarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RemoveMusicianAvatarUseCaseImpl implements RemoveMusicianAvatarUseCase {

    private final MusicianRepository musicianRepository;
    private final MusicianMethodValidator musicianMethodValidator;

    @Override
    public void execute(String musicianUuid) {
        var toValidate = new MusicianValidationDto(MethodValidationType.REMOVE_FILE, musicianUuid);
        musicianMethodValidator.callProcesses(toValidate);

        var musician = musicianRepository.findById(musicianUuid).orElseThrow(MusicianDoesNotExistException::new);

        musician.setAvatarUuid(null);
        musician.setUpdateDate(LocalDateTime.now());

        musicianRepository.save(musician);
    }
}
