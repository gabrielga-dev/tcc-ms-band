package br.com.events.band.data.io.music.request;

import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicRequestMock {

    public static MusicRequest build() {
        return MusicRequest
                .builder()
                .name(MockConstants.STRING)
                .author(MockConstants.STRING)
                .artist(MockConstants.STRING)
                .observation(MockConstants.STRING)
                .build();
    }
}
