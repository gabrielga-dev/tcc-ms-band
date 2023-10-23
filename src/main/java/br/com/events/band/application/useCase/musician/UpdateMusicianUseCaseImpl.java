package br.com.events.band.application.useCase.musician;

import br.com.events.band.application.process.musician.exception.MusicianNotPresentOnBandException;
import br.com.events.band.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.domain.io._new.musician.form.MusicianForm;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.domain.type.MethodValidationType;
import br.com.events.band.infrastructure.process.musician.MusicianMethodValidator;
import br.com.events.band.infrastructure.useCase.musician.UpdateMusicianUseCase;
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
