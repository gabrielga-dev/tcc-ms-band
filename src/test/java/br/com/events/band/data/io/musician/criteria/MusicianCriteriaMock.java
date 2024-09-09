package br.com.events.band.data.io.musician.criteria;

import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicianCriteriaMock {

    public static MusicianCriteria build() {
        return new MusicianCriteria(
                MockConstants.STRING,
                MockConstants.STRING,
                MockConstants.STRING,
                MockConstants.LONG,
                MockConstants.STRING,
                MockConstants.STRING,
                MockConstants.STRING
        );
    }
}
