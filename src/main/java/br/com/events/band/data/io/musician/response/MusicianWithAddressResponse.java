package br.com.events.band.data.io.musician.response;

import br.com.events.band.data.io.address.response.AddressResponse;
import br.com.events.band.data.model.table.MusicianTable;
import lombok.Getter;

@Getter
public class MusicianWithAddressResponse extends MusicianResponse {

    private final AddressResponse address;

    public MusicianWithAddressResponse(MusicianTable musician, AddressResponse address) {
        super(musician, null);
        this.address = address;
    }
}
