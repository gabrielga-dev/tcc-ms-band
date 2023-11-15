package br.com.events.band.older.domain.io.music.update;

import br.com.events.band.older.domain.io.music.create.in.CreateMusicForm;
import br.com.events.band.older.domain.io.music.update.in.UpdateMusicUseCaseForm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UpdateMusicMapper {

    public static UpdateMusicUseCaseForm from(String musicUuid, CreateMusicForm music) {
        return UpdateMusicUseCaseForm
                .builder()
                .musicUuid(musicUuid)
                .name(music.getName())
                .observation(music.getObservation())
                .build();
    }
}
