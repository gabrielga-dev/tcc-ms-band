package br.com.events.band.data.io.file;

import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileDTOMock {

    public static FileDTO build() {
        return new FileDTO(MockConstants.STRING, MockConstants.STRING);
    }
}
