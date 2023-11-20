package br.com.events.band.newer.data.io.musician.response;

import br.com.events.band.newer.data.io.address.response.AddressResponse;
import br.com.events.band.newer.data.model.table.MusicianTable;
import lombok.Getter;

@Getter
public class MusicianWithAddressResponse extends MusicianResponse {

    private final AddressResponse address;

    public MusicianWithAddressResponse(MusicianTable musician, AddressResponse address) {
        super(musician, null);
        this.address = address;
    }
}
