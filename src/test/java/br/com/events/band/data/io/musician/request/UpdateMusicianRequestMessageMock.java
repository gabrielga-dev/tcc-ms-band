package br.com.events.band.data.io.musician.request;

import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UpdateMusicianRequestMessageMock {

    public static UpdateMusicianRequestMessage build() {
        var toReturn = new UpdateMusicianRequestMessage();

        toReturn.setFirstName(MockConstants.STRING);
        toReturn.setLastName(MockConstants.STRING);
        toReturn.setCpf(MockConstants.STRING);
        toReturn.setEmail(MockConstants.STRING);
        toReturn.setPersonUuid(MockConstants.STRING);

        return toReturn;
    }
}
