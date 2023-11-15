package br.com.events.band.older.application.useCase.band;

import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.older.domain.repository.BandRepository;
import br.com.events.band.older.infrastructure.useCase.band.RemoveBandProfilePictureUseCase;
import br.com.events.band.older.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RemoveBandProfilePictureUseCaseImpl implements RemoveBandProfilePictureUseCase {

    private final BandRepository bandRepository;

    @Override
    public Void execute(String bandUuid) {
        var userUuid = AuthUtil.getAuthenticatedPerson().getUuid();
        var band = bandRepository.findByUuidAndActiveTrue(bandUuid)
                .orElseThrow(BandNonExistenceException::new);
        if (!Objects.equals(band.getOwnerUuid(), userUuid)) {
            throw new BandOwnerException();
        }

        band.setProfilePictureUuid(null);
        bandRepository.save(band);

        return null;
    }
}
