package br.com.events.band.application.process.musician.create.validation;

import br.com.events.band.application.process.exception.BandNonExistenceException;
import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.domain.io.musician.create.useCase.in.CreateMusicianUseCaseForm;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.infrastructure.process.musician.create.CreateMusicianValidation;
import br.com.events.band.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandOwnerCreateMusicianValidation implements CreateMusicianValidation {

    private final BandRepository bandRepository;

    @Override
    public void validate(CreateMusicianUseCaseForm toValidate) {
        var band = bandRepository.findById(toValidate.getBandUuid())
                .orElseThrow(BandNonExistenceException::new);

        if (!band.getOwnerUuid().equals(AuthUtil.getAuthenticatedPerson().getUuid())){
            throw new BandOwnerException();
        }
    }
}
