package br.com.events.band.newer.data.io.address.response;

import br.com.events.band.newer.data.io.address.city.CityResponse;
import br.com.events.band.older.domain.entity.address.BandAddress;
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

    public AddressResponse(BandAddress address, CityResponse city) {
        this.street = address.getStreet();
        this.neighbour = address.getNeighbour();
        this.complement = address.getComplement();
        this.city = city.getName();
        this.state = address.getState();
        this.country = address.getCountry();
        this.zipCode = address.getZipCode();
    }
}
