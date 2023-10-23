package br.com.events.band.infrastructure.useCase.musician;

import br.com.events.band.domain.io.UuidHolderDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadMusicianAvatarUseCase {

    UuidHolderDTO execute(String musicianUuid, MultipartFile file);
}
