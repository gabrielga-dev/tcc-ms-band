package br.com.events.band.application.useCase.band;

import br.com.events.band.application.process.exception.BandNonExistenceException;
import br.com.events.band.application.process.exception.BandOwnerException;
import br.com.events.band.domain.io.UuidHolderDTO;
import br.com.events.band.domain.io.feign.msFile.uploadFile.in.FileTypeFileClient;
import br.com.events.band.domain.repository.BandRepository;
import br.com.events.band.domain.type.FileOriginType;
import br.com.events.band.infrastructure.feign.msFile.FileFeignClient;
import br.com.events.band.infrastructure.useCase.band.UploadBandProfilePictureUseCase;
import br.com.events.band.util.AuthUtil;
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
                FileTypeFileClient.IMAGE,
                file
        );

        band.setProfilePictureUuid(result.getUuid());
        bandRepository.save(band);

        return new UuidHolderDTO(band.getProfilePictureUuid());
    }
}
