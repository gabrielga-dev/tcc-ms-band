package br.com.events.band.older.domain.entity.address;

import br.com.events.band.newer.data.table.BandTable;
import br.com.events.band.older.domain.io._new.address.form.AddressForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "band_address")
public class BandAddress {

    @Id
    @Column(name = "uuid", nullable = false)
    private String uuid = UUID.randomUUID().toString();

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

    @OneToOne
    @JoinColumn(name = "band_uuid", referencedColumnName = "uuid")
    private BandTable band;

    public BandAddress(AddressForm address) {
        this.street = (address.getStreet());
        this.neighbour = (address.getNeighbour());
        this.complement = (address.getComplement());
        this.city = (address.getCityId());
        this.state = (address.getStateIso());
        this.country = (address.getCountryIso());
        this.zipCode = (address.getZipCode());
    }
}
