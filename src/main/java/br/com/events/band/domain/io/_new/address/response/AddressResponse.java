package br.com.events.band.domain.io._new.address.response;

import br.com.events.band.domain.entity.address.MusicianAddress;
import br.com.events.band.domain.io.feign.msLocation.getCityByIdAndStateAndCountryIso.out.GetCityByIdAndStateAndCountryIsoMsLocationResponse;
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

    public AddressResponse(MusicianAddress address, GetCityByIdAndStateAndCountryIsoMsLocationResponse city) {
        this.street = address.getStreet();
        this.neighbour = address.getNeighbour();
        this.complement = address.getComplement();
        this.city = city.getName();
        this.state = address.getState();
        this.country = address.getCountry();
        this.zipCode = address.getZipCode();
    }
}
