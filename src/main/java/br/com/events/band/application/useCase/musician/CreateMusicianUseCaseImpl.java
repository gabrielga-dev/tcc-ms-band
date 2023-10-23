package br.com.events.band.application.useCase.musician;

import br.com.events.band.application.useCase.band.exception.BandNotFoundException;
import br.com.events.band.domain.entity.Musician;
import br.com.events.band.domain.io.UuidHolderDTO;
import br.com.events.band.domain.io._new.musician.form.MusicianForm;
import br.com.events.band.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.domain.type.MethodValidationType;
import br.com.events.band.infrastructure.process.musician.MusicianMethodValidator;
import br.com.events.band.infrastructure.useCase.musician.CreateMusicianUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements the feature that creates new musicians
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreateMusicianUseCaseImpl implements CreateMusicianUseCase {

    private final BandRepository bandRepository;
    private final MusicianRepository musicianRepository;

    private final MusicianMethodValidator musicianMethodValidator;

    @Override
    public UuidHolderDTO execute(MusicianForm musicianForm, String bandUuid) {
        var validationDto = new MusicianValidationDto(bandUuid, MethodValidationType.CREATE, musicianForm);
        musicianMethodValidator.callProcesses(validationDto);

        var toSave = new Musician(musicianForm);

        toSave.setBand(bandRepository.findById(bandUuid).orElseThrow(BandNotFoundException::new));

        var saved = musicianRepository.save(toSave);

        return new UuidHolderDTO(saved.getUuid());
    }
}
