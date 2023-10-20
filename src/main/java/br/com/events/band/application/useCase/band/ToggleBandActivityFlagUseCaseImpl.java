package br.com.events.band.application.useCase.band;

import br.com.events.band.application.process.contact.exception.BandContactNonExistenceException;
import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.infrastructure.useCase.band.ToggleBandActivityFlagUseCase;
import br.com.events.band.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ToggleBandActivityFlagUseCaseImpl implements ToggleBandActivityFlagUseCase {

    private final BandRepository bandRepository;

    @Override
    public Void execute(String bandUuid) {
        var userUuid = AuthUtil.getAuthenticatedPerson().getUuid();
        var band = bandRepository.findById(bandUuid).orElseThrow(BandContactNonExistenceException::new);

        if(!Objects.equals(band.getOwnerUuid(), userUuid)){
            throw new BandOwnerException();
        }

        band.setActive(!band.getActive());
        band.setUpdateDate(LocalDateTime.now());

        bandRepository.save(band);

        return null;
    }
}
