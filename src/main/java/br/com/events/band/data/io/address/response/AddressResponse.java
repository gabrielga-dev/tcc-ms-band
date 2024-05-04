package br.com.events.band.data.io.address.response;

import br.com.events.band.data.io.address.IAddress;
import br.com.events.band.data.io.address.city.CityResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class AddressResponse {

    private String street;
    private String neighbour;
    private Integer number;
    private String complement;
    private String city;
    private Long cityId;
    private String state;
    private String country;
    private String zipCode;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public AddressResponse(IAddress address, CityResponse city) {
        this.street = address.getStreet();
        this.neighbour = address.getNeighbour();
        this.number = address.getNumber();
        this.complement = address.getComplement();
        this.city = city.getName();
        this.cityId = city.getId();
        this.state = address.getStateIso();
        this.country = address.getCountryIso();
        this.zipCode = address.getZipCode();
    }

    @JsonIgnore
    public String getFormattedAddress() {
        return String.format(
                "%s , %d%s %s (%s)",
                street,
                number,
                Objects.nonNull(complement) ? " (" + complement + ")" : "",
                neighbour,
                zipCode
        );
    }
}
