package br.com.events.band.older.infrastructure.useCase.band;

import br.com.events.band.older.domain.io.UuidHolderDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadBandProfilePictureUseCase {

    UuidHolderDTO execute(String bandUuid, MultipartFile file);
}
