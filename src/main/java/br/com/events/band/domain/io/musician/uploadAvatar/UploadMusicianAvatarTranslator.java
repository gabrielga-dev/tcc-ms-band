package br.com.events.band.domain.io.musician.uploadAvatar;

import br.com.events.band.domain.io.feign.msFile.uploadFile.out.UploadFileFileClientResult;
import br.com.events.band.domain.io.musician.uploadAvatar.out.UploadMusicianAvatarResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UploadMusicianAvatarTranslator {

    public static UploadMusicianAvatarResult from(UploadFileFileClientResult origin){
        return new UploadMusicianAvatarResult(origin.getUuid());
    }
}
