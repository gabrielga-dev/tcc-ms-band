package br.com.events.band.application.process.musician.update.validation;

import br.com.events.band.application.process.exception.BandNonExistenceException;
import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.domain.io.musician.update.useCase.in.UpdateMusicianUseCaseForm;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.infrastructure.process.musician.update.UpdateMusicianValidation;
import br.com.events.band.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandOwnerUpdateMusicianValidation implements UpdateMusicianValidation {

    private final BandRepository bandRepository;

    @Override
    public void validate(UpdateMusicianUseCaseForm toValidate) {
        var band = bandRepository.findById(toValidate.getBandUuid())
                .orElseThrow(BandNonExistenceException::new);

        if (!band.getOwnerUuid().equals(AuthUtil.getAuthenticatedPerson().getUuid())){
            throw new BandOwnerException();
        }
    }
}
