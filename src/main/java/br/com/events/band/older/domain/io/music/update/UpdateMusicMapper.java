package br.com.events.band.older.domain.io.music.update;

import br.com.events.band.newer.data.io.music.request.MusicRequest;
import br.com.events.band.older.domain.io.music.update.in.UpdateMusicUseCaseForm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UpdateMusicMapper {

    public static UpdateMusicUseCaseForm from(String musicUuid, MusicRequest music) {
        return UpdateMusicUseCaseForm
                .builder()
                .musicUuid(musicUuid)
                .name(music.getName())
                .observation(music.getObservation())
                .build();
    }
}
