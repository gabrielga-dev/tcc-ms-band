package br.com.events.band.older.application.process.music.create.validations;

import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.older.domain.io.music.create.in.CreateMusicUseCaseForm;
import br.com.events.band.older.domain.repository.BandRepository;
import br.com.events.band.older.infrastructure.process.music.create.CreateMusicValidation;
import br.com.events.band.older.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandOwnerCreateMusicValidation implements CreateMusicValidation {

    private final BandRepository bandRepository;

    @Override
    public void validate(CreateMusicUseCaseForm toValidate) {
        var band = bandRepository.findById(toValidate.getBandUuid())
                .orElseThrow(BandNonExistenceException::new);

        if (!band.getOwnerUuid().equals(AuthUtil.getAuthenticatedPerson().getUuid())){
            throw new BandOwnerException();
        }
    }
}
