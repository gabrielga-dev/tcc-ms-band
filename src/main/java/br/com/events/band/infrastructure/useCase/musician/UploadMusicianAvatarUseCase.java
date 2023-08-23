package br.com.events.band.infrastructure.useCase.musician;

import br.com.events.band.domain.io.musician.uploadAvatar.in.UploadMusicianAvatarRequest;
import br.com.events.band.domain.io.musician.uploadAvatar.out.UploadMusicianAvatarResult;
import br.com.events.band.infrastructure.useCase.UseCaseBase;
import org.springframework.web.multipart.MultipartFile;

public interface UploadMusicianAvatarUseCase
        extends UseCaseBase<UploadMusicianAvatarRequest, UploadMusicianAvatarResult> {

    default UploadMusicianAvatarResult execute(String uuid, MultipartFile file){
        var request = UploadMusicianAvatarRequest
                .builder()
                .musicianUuid(uuid)
                .file(file)
                .build();
        return this.execute(request);
    }
}
