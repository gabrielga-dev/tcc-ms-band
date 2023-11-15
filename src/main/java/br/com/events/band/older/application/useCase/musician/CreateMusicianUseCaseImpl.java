package br.com.events.band.older.application.useCase.musician;

import br.com.events.band.older.application.useCase.band.exception.BandNotFoundException;
import br.com.events.band.newer.data.table.MusicianTable;
import br.com.events.band.older.domain.io.UuidHolderDTO;
import br.com.events.band.older.domain.io._new.musician.form.MusicianForm;
import br.com.events.band.older.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.older.domain.repository.BandRepository;
import br.com.events.band.older.domain.repository.MusicianRepository;
import br.com.events.band.older.domain.type.MethodValidationType;
import br.com.events.band.older.infrastructure.process.musician.MusicianMethodValidator;
import br.com.events.band.older.infrastructure.useCase.musician.CreateMusicianUseCase;
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

        var toSave = new MusicianTable(musicianForm);

        toSave.setBand(bandRepository.findById(bandUuid).orElseThrow(BandNotFoundException::new));

        var saved = musicianRepository.save(toSave);

        return new UuidHolderDTO(saved.getUuid());
    }
}
