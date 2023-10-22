package br.com.events.band.application.useCase.musician;

import br.com.events.band.application.useCase.band.exception.BandNotFoundException;
import br.com.events.band.domain.io.UuidHolderDTO;
import br.com.events.band.domain.io.musician.create.useCase.in.CreateMusicianUseCaseForm;
import br.com.events.band.domain.mapper.musician.CreateMusicianMapper;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.create.CreateMusicianValidator;
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
    private final CreateMusicianValidator createMusicianValidator;

    @Override
    public UuidHolderDTO execute(CreateMusicianUseCaseForm param) {
        createMusicianValidator.callProcesses(param);

        var toSave = CreateMusicianMapper.toEntity(param);
        toSave.setBand(
                bandRepository.findById(param.getBandUuid())
                        .orElseThrow(BandNotFoundException::new)
        );

        var saved = musicianRepository.save(toSave);

        return new UuidHolderDTO(saved.getUuid());
    }
}
