package br.com.events.band.application.process.musician.create.validation;

import br.com.events.band.application.process.musician.exception.DuplicationAtMusicianCpfException;
import br.com.events.band.domain.io.musician.create.useCase.in.CreateMusicianUseCaseForm;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.create.CreateMusicianValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CpfUniqueAtBandCreateMusicianValidation implements CreateMusicianValidation {

    private final MusicianRepository musicianRepository;

    @Override
    public void validate(CreateMusicianUseCaseForm toValidate) {
        var exists = musicianRepository.existsByCpfAndBandUuidAndActiveTrue(toValidate.getCpf(), toValidate.getBandUuid());
        if (exists) {
            throw new DuplicationAtMusicianCpfException();
        }
    }
}
