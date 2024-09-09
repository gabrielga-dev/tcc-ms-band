package br.com.events.band.data.io.event.response;


import br.com.events.band.MockConstants;
import br.com.events.band.data.io.address.response.AddressResponseMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EventProfileResponseMock {

    public static EventProfileResponse build() {
        var toReturn = new EventProfileResponse();

        toReturn.setUuid(MockConstants.STRING);
        toReturn.setName(MockConstants.STRING);
        toReturn.setDescription(MockConstants.STRING);
        toReturn.setDateTimeStamp(MockConstants.LONG);
        toReturn.setAddress(AddressResponseMock.build());

        return toReturn;
    }
}
