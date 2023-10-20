package br.com.events.band.application.useCase.band;

import br.com.events.band.application.process.exception.BandNonExistenceException;
import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.domain.io.UuidHolderDTO;
import br.com.events.band.domain.io.feign.msFile.uploadFile.in.FileTypeFileClient;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.domain.type.FileOriginType;
import br.com.events.band.infrastructure.useCase.band.RemoveBandProfilePictureUseCase;
import br.com.events.band.util.AuthUtil;
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
