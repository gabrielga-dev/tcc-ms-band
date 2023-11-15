package br.com.events.band.older.domain.io.musician.uploadAvatar;

import br.com.events.band.newer.data.io.file.FileDTO;
import br.com.events.band.older.domain.io.musician.uploadAvatar.out.UploadMusicianAvatarResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UploadMusicianAvatarTranslator {

    public static UploadMusicianAvatarResult from(FileDTO origin){
        return new UploadMusicianAvatarResult(origin.getUuid());
    }
}
