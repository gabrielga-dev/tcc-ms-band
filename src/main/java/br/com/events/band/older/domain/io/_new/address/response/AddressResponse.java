package br.com.events.band.older.domain.io._new.address.response;

import br.com.events.band.newer.data.table.addresses.MusicianAddressTable;
import br.com.events.band.newer.data.io.address.city.CityResponse;
import lombok.Getter;

@Getter
public class AddressResponse {

    private final String street;
    private final String neighbour;
    private final String complement;
    private final String city;
    private final String state;
    private final String country;
    private final String zipCode;

    public AddressResponse(MusicianAddressTable address, CityResponse city) {
        this.street = address.getStreet();
        this.neighbour = address.getNeighbour();
        this.complement = address.getComplement();
        this.city = city.getName();
        this.state = address.getState();
        this.country = address.getCountry();
        this.zipCode = address.getZipCode();
    }
}
