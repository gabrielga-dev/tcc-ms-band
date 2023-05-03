package br.com.events.band.application.useCase.musician;

import br.com.events.band.application.process.musician.exception.MusicianNotPresentOnBandException;
import br.com.events.band.domain.io.musician.delete.in.DeleteMusicianUseCaseForm;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.delete.DeleteMusicianValidator;
import br.com.events.band.infrastructure.useCase.musician.DeleteMusiciansUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteMusiciansUseCaseImpl implements DeleteMusiciansUseCase {

    private final MusicianRepository musicianRepository;
    private final DeleteMusicianValidator deleteMusicianValidator;

    @Override
    public Void execute(DeleteMusicianUseCaseForm param) {
        deleteMusicianValidator.callProcesses(param);

        var musician = musicianRepository.findById(param.getMusicianUuid())
                .orElseThrow(MusicianNotPresentOnBandException::new);

        musician.setActive(Boolean.FALSE);

        musicianRepository.save(musician);

        return null;
    }
}
