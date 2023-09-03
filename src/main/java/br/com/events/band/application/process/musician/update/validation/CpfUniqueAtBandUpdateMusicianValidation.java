package br.com.events.band.application.process.musician.update.validation;

import br.com.events.band.application.process.musician.exception.DuplicationAtMusicianCpfException;
import br.com.events.band.domain.io.musician.update.useCase.in.UpdateMusicianUseCaseForm;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.update.UpdateMusicianValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CpfUniqueAtBandUpdateMusicianValidation implements UpdateMusicianValidation {

    private final MusicianRepository musicianRepository;

    @Override
    public void validate(UpdateMusicianUseCaseForm toValidate) {
        var musicianOpt = musicianRepository.findByCpf(toValidate.getCpf());
        if (musicianOpt.isPresent() && !Objects.equals(musicianOpt.get().getUuid(), toValidate.getMusicianUuid())) {
            throw new DuplicationAtMusicianCpfException();
        }
    }
}
