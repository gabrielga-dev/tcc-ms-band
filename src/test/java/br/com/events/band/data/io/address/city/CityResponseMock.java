package br.com.events.band.data.io.address.city;

import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CityResponseMock {

    public static CityResponse build(){
        return new CityResponse(MockConstants.LONG, MockConstants.STRING);
    }
}
