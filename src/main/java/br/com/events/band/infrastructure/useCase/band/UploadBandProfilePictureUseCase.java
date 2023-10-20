package br.com.events.band.infrastructure.useCase.band;

import br.com.events.band.domain.io.UuidHolderDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadBandProfilePictureUseCase {

    UuidHolderDTO execute(String bandUuid, MultipartFile file);
}
