package br.com.events.band.application.process.musician.validations;

import br.com.events.band.application.process.musician.exception.DuplicationAtMusicianCpfException;
import br.com.events.band.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.infrastructure.process.musician.MusicianMethodValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static br.com.events.band.domain.type.MethodValidationType.CREATE;
import static br.com.events.band.domain.type.MethodValidationType.EDIT;

@Component
@RequiredArgsConstructor
public class CpfUniqueAtBandMusicianValidation implements MusicianMethodValidation {

    private final MusicianRepository musicianRepository;

    @Override
    public boolean matches(MusicianValidationDto dto) {
        return List.of(CREATE, EDIT).contains(dto.getMethodType());
    }

    @Override
    public void validate(MusicianValidationDto toValidate) {
        var musicianOpt = musicianRepository.findByCpf(toValidate.getForm().getCpf());

        if (
                (EDIT.equals(toValidate.getMethodType())
                        && musicianOpt.isPresent()
                        && !Objects.equals(musicianOpt.get().getUuid(), toValidate.getMusicianUuid()))
                        || (EDIT.equals(toValidate.getMethodType()) && musicianOpt.isPresent())
        ) {
            throw new DuplicationAtMusicianCpfException();
        }
    }
}
