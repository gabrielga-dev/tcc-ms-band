package br.com.events.band.data.io.band.request;

import br.com.events.band.MockConstants;
import br.com.events.band.data.io.address.request.AddressRequestMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UpdateBandRequestMock {

    public static UpdateBandRequest build() {
        return UpdateBandRequest
                .builder()
                .name(MockConstants.STRING)
                .description(MockConstants.STRING)
                .address(AddressRequestMock.build())
                .clearProfilePicture(MockConstants.BOOLEAN)
                .build();
    }
}
