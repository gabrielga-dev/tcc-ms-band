package br.com.events.band.data.io.musician.request;

import br.com.events.band.MockConstants;
import br.com.events.band.data.io.address.request.AddressRequestMock;
import br.com.events.band.data.io.musician_type.request.MusicianTypeRequestMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicianRequestMock {

    public static MusicianRequest build() {
        return MusicianRequest
                .builder()
                .firstName(MockConstants.STRING)
                .lastName(MockConstants.STRING)
                .birthday(MockConstants.LONG)
                .cpf(MockConstants.STRING)
                .email(MockConstants.STRING)
                .address(AddressRequestMock.build())
                .types(List.of(MusicianTypeRequestMock.build()))
                .build();
    }
}
