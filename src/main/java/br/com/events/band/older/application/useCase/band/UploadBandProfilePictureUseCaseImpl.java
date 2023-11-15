package br.com.events.band.older.application.useCase.band;

import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.older.domain.io.UuidHolderDTO;
import br.com.events.band.newer.data.io.file.FileType;
import br.com.events.band.older.domain.repository.BandRepository;
import br.com.events.band.newer.data.io.file.FileOriginType;
import br.com.events.band.older.infrastructure.feign.msFile.FileFeignClient;
import br.com.events.band.older.infrastructure.useCase.band.UploadBandProfilePictureUseCase;
import br.com.events.band.older.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UploadBandProfilePictureUseCaseImpl implements UploadBandProfilePictureUseCase {

    private final BandRepository bandRepository;
    private final FileFeignClient fileFeignClient;

    @Override
    @Transactional
    public UuidHolderDTO execute(String bandUuid, MultipartFile file) {
        var userUuid = AuthUtil.getAuthenticatedPerson().getUuid();
        var band = bandRepository.findByUuidAndActiveTrue(bandUuid)
                .orElseThrow(BandNonExistenceException::new);
        if (!Objects.equals(band.getOwnerUuid(), userUuid)) {
            throw new BandOwnerException();
        }

        var result = fileFeignClient.uploadFile(
                FileOriginType.BAND.name(),
                bandUuid,
                FileType.IMAGE,
                file
        );

        band.setProfilePictureUuid(result.getUuid());
        bandRepository.save(band);

        return new UuidHolderDTO(band.getProfilePictureUuid());
    }
}
