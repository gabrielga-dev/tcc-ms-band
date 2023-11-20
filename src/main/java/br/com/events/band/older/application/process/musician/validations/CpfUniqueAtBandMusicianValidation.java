package br.com.events.band.older.application.process.musician.validations;

import br.com.events.band.older.application.process.musician.exception.DuplicationAtMusicianCpfException;
import br.com.events.band.older.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.newer.adapter.repository.jpa.MusicianJpaRepository;
import br.com.events.band.older.infrastructure.process.musician.MusicianMethodValidation;
import br.com.events.band.older.domain.type.MethodValidationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CpfUniqueAtBandMusicianValidation implements MusicianMethodValidation {

    private final MusicianJpaRepository musicianRepository;

    @Override
    public boolean matches(MusicianValidationDto dto) {
        return List.of(MethodValidationType.CREATE, MethodValidationType.EDIT).contains(dto.getMethodType());
    }

    @Override
    public void validate(MusicianValidationDto toValidate) {
        var musicianOpt = musicianRepository.findByCpf(toValidate.getForm().getCpf());

        if (
                (MethodValidationType.EDIT.equals(toValidate.getMethodType())
                        && musicianOpt.isPresent()
                        && !Objects.equals(musicianOpt.get().getUuid(), toValidate.getMusicianUuid()))
                        || (MethodValidationType.EDIT.equals(toValidate.getMethodType()) && musicianOpt.isPresent())
        ) {
            throw new DuplicationAtMusicianCpfException();
        }
    }
}
