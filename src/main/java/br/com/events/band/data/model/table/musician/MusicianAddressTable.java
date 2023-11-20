package br.com.events.band.data.model.table.musician;

import br.com.events.band.data.io.address.IAddress;
import br.com.events.band.data.io.address.request.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * This class represents the event address's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "musician_address")
public class MusicianAddressTable implements IAddress {

    @Id
    @Column(name = "uuid", nullable = false)
    private final String uuid = UUID.randomUUID().toString();

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "neighbour", nullable = false)
    private String neighbour;

    @Column(name = "complement")
    private String complement;

    @Column(name = "city", nullable = false)
    private Long city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musician_uuid")
    private MusicianTable musician;

    public MusicianAddressTable(AddressRequest address) {
        this.street = address.getStreet();
        this.neighbour = address.getNeighbour();
        this.complement = address.getComplement();
        this.city = address.getCityId();
        this.state = address.getStateIso();
        this.country = address.getCountryIso();
        this.zipCode = address.getZipCode();
        this.latitude = null;
        this.longitude = null;
    }

    public void transferData(AddressRequest address) {
        this.setStreet(address.getStreet());
        this.setNeighbour(address.getNeighbour());
        this.setComplement(address.getComplement());
        this.setCity(address.getCityId());
        this.setState(address.getStateIso());
        this.setCountry(address.getCountryIso());
        this.setZipCode(address.getZipCode());
    }

    @Override
    public Long getCityId() {
        return this.city;
    }

    @Override
    public String getStateIso() {
        return state;
    }

    @Override
    public String getCountryIso() {
        return this.country;
    }
}
