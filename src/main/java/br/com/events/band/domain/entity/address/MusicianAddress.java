package br.com.events.band.domain.entity.address;

import br.com.events.band.domain.entity.Musician;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "musician_address")
public class MusicianAddress {

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musician_uuid")
    private Musician musician;
}
