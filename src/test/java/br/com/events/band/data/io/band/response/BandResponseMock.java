package br.com.events.band.data.io.band.response;

import br.com.events.band.data.model.table.band.BandTableMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BandResponseMock {

    public static BandResponse build() {
        return new BandResponse(BandTableMock.build());
    }
}
