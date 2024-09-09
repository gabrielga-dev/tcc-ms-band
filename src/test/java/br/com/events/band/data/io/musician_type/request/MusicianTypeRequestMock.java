package br.com.events.band.data.io.musician_type.request;


import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicianTypeRequestMock {

    public static MusicianTypeRequest build() {
        return new MusicianTypeRequest(MockConstants.STRING);
    }
}
