package br.com.events.band.newer.data.table;

import br.com.events.band.newer.core.util.AuthUtil;
import br.com.events.band.newer.data.io.band.IBandResponse;
import br.com.events.band.newer.data.io.band.request.UpdateBandRequest;
import br.com.events.band.older.domain.entity.address.BandAddress;
import br.com.events.band.newer.data.io.band.request.BandRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class BandTable implements IBandResponse {

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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "band", cascade = CascadeType.ALL)
    private BandAddress address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "band", cascade = CascadeType.ALL)
    private List<ContactTable> contacts;

    public BandTable(BandRequest form) {
        this.active = true;
        this.creationDate = LocalDateTime.now();
        this.name = form.getName();
        this.description = form.getDescription();
        this.address = new BandAddress(form.getAddress());
        this.address.setBand(this);
        this.ownerUuid = AuthUtil.getAuthenticatedPersonUuid();

        this.contacts = form.getContacts()
                .stream()
                .map(c -> {
                    var newContact = new ContactTable(c);
                    newContact.setBand(this);
                    return newContact;
                })
                .collect(Collectors.toList());
    }

    public void update(UpdateBandRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.address.update(request.getAddress());
        this.updateDate = LocalDateTime.now();
    }
}
