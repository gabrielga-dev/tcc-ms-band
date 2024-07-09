package br.com.events.band.data.io.band.request;

import br.com.events.band.MockConstants;
import br.com.events.band.data.io.address.request.AddressRequestMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BandRequestMock {

    public static BandRequest build() {
        return BandRequest
                .builder()
                .name(MockConstants.STRING)
                .description(MockConstants.STRING)
                .address(AddressRequestMock.build())
                .contacts(new ArrayList<>())
                .build();
    }
}
