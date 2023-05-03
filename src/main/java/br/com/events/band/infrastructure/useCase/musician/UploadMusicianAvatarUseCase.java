package br.com.events.band.infrastructure.useCase.musician;

import br.com.events.band.domain.io.musician.uploadAvatar.in.UploadMusicianAvatarRequest;
import br.com.events.band.domain.io.musician.uploadAvatar.out.UploadMusicianAvatarResult;
import br.com.events.band.infrastructure.useCase.UseCaseBase;

public interface UploadMusicianAvatarUseCase
        extends UseCaseBase<UploadMusicianAvatarRequest, UploadMusicianAvatarResult> {
}
