package br.com.events.band.data.io.music.criteria;

import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicCriteriaMock {

    public static MusicCriteria build(){
        return new MusicCriteria(
                MockConstants.STRING,
                MockConstants.STRING,
                MockConstants.STRING
        );
    }
}
