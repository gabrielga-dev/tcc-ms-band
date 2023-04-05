package br.com.events.band.application.process.musician.delete.validation;

import br.com.events.band.application.process.exception.BandNonExistenceException;
import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.domain.io.musician.delete.useCase.in.DeleteMusicianUseCaseForm;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.infrastructure.process.musician.delete.DeleteMusicianValidation;
import br.com.events.band.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class BandOwnerDeleteMusicianValidation implements DeleteMusicianValidation {

    private final BandRepository bandRepository;

    @Override
    public void validate(DeleteMusicianUseCaseForm toValidate) {
        var band = bandRepository.findById(toValidate.getBandUuid())
                .orElseThrow(
                        BandNonExistenceException::new
                );

        var userUuid = AuthUtil.getAuthenticatedPerson().getUuid();

        if (!Objects.equals(userUuid, band.getOwnerUuid())){
            throw new BandOwnerException();
        }
    }
}
