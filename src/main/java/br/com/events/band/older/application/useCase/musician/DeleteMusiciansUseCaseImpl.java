package br.com.events.band.older.application.useCase.musician;

import br.com.events.band.older.application.process.musician.exception.MusicianNotPresentOnBandException;
import br.com.events.band.older.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.older.domain.repository.MusicianRepository;
import br.com.events.band.older.domain.type.MethodValidationType;
import br.com.events.band.older.infrastructure.process.musician.MusicianMethodValidator;
import br.com.events.band.older.infrastructure.useCase.musician.DeleteMusiciansUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteMusiciansUseCaseImpl implements DeleteMusiciansUseCase {

    private final MusicianRepository musicianRepository;

    private final MusicianMethodValidator musicianMethodValidator;

    @Override
    public void execute(String bandUuid, String musicianUuid) {
        var validationDto = new MusicianValidationDto(bandUuid, MethodValidationType.DELETE, musicianUuid);
        musicianMethodValidator.callProcesses(validationDto);

        var musician = musicianRepository.findById(musicianUuid)
                .orElseThrow(MusicianNotPresentOnBandException::new);

        musician.setActive(Boolean.FALSE);

        musicianRepository.save(musician);
    }
}
