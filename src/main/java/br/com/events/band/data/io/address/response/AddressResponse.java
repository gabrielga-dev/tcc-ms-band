package br.com.events.band.data.io.address.response;

import br.com.events.band.data.io.address.IAddress;
import br.com.events.band.data.io.address.city.CityResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponse {

    private String street;
    private String neighbour;
    private String complement;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public AddressResponse(IAddress address, CityResponse city) {
        this.street = address.getStreet();
        this.neighbour = address.getNeighbour();
        this.complement = address.getComplement();
        this.city = city.getName();
        this.state = address.getStateIso();
        this.country = address.getCountryIso();
        this.zipCode = address.getZipCode();
    }
}
