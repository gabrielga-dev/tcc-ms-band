package br.com.events.band.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.events.band.domain.entity.address.BandAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the band's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "band")
public class Band {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.TRUE;

    @Column(name = "owner_uuid", nullable = false)
    private String ownerUuid;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "band", cascade = CascadeType.ALL)
    private List<Musician> musicians;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "band", cascade = CascadeType.ALL)
    private BandAddress address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "band", cascade = CascadeType.ALL)
    private List<Contact> contacts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "band", cascade = CascadeType.ALL)
    private List<Music> musics;
}
