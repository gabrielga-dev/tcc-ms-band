package br.com.events.band.older.application.useCase.musician;

import br.com.events.band.older.application.process.musician.exception.MusicianNotPresentOnBandException;
import br.com.events.band.older.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.older.domain.io._new.musician.form.MusicianForm;
import br.com.events.band.older.domain.repository.MusicianRepository;
import br.com.events.band.older.domain.type.MethodValidationType;
import br.com.events.band.older.infrastructure.process.musician.MusicianMethodValidator;
import br.com.events.band.older.infrastructure.useCase.musician.UpdateMusicianUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateMusicianUseCaseImpl implements UpdateMusicianUseCase {

    private final MusicianRepository musicianRepository;

    private final MusicianMethodValidator musicianMethodValidator;

    @Override
    public void execute(String bandUuid, String musicianUuid, MusicianForm form) {
        var validationDto = new MusicianValidationDto(bandUuid, MethodValidationType.EDIT, musicianUuid, form);
        musicianMethodValidator.callProcesses(validationDto);

        var musician = musicianRepository.findById(musicianUuid)
                .orElseThrow(MusicianNotPresentOnBandException::new);

        musician.transferData(form);

        musicianRepository.save(musician);
    }
}
