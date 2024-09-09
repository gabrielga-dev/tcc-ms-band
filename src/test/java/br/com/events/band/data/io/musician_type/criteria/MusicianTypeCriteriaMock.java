package br.com.events.band.data.io.musician_type.criteria;

import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicianTypeCriteriaMock {

    public static MusicianTypeCriteria build() {
        return new MusicianTypeCriteria(MockConstants.STRING);
    }
}
