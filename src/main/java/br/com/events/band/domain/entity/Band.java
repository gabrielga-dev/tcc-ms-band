package br.com.events.band.domain.entity;

import br.com.events.band.domain.entity.address.BandAddress;
import br.com.events.band.domain.io._new.band.form.BandForm;
import br.com.events.band.domain.io.auth.AuthenticatedPerson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Column(name = "profile_picture_uuid", nullable = false)
    private String profilePictureUuid;

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

    public Band(BandForm form) {
        this.active = true;
        this.creationDate = LocalDateTime.now();
        this.name = form.getName();
        this.description = form.getDescription();
        this.address = new BandAddress(form.getAddress());
        this.address.setBand(this);
        this.ownerUuid = ((AuthenticatedPerson) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUuid();
        this.contacts = form.getContacts()
                .stream()
                .map(c -> {
                    var newContact = new Contact(c);
                    newContact.setBand(this);
                    return newContact;
                })
                .collect(Collectors.toList());
    }
}
