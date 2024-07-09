package br.com.events.band.data.model.table.band;

import br.com.events.band.data.io.band.IBandResponse;
import br.com.events.band.data.io.band.request.BandRequest;
import br.com.events.band.data.io.band.request.UpdateBandRequest;
import br.com.events.band.data.model.ActionableTable;
import br.com.events.band.data.model.TableWithProfilePicture;
import br.com.events.band.data.model.UpdatableTable;
import br.com.events.band.data.model.table.band.contact.ContactTable;
import br.com.events.band.data.model.table.music.MusicTable;
import br.com.events.band.data.model.table.musician.MusicianTable;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import lombok.AllArgsConstructor;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "band")
public class BandTable implements
        IBandResponse, ActionableTable, UpdatableTable<UpdateBandRequest>, TableWithProfilePicture {

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

    @Column(name = "avatar_picture_uuid")
    private String profilePictureUuid;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "band", cascade = CascadeType.ALL)
    private BandAddressTable address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "band", cascade = CascadeType.ALL)
    private List<ContactTable> contacts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bandThatInserted", cascade = CascadeType.ALL)
    private List<MusicianTable> insertedMusicians = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "band", cascade = CascadeType.ALL)
    private List<BandMusicianTable> associatedMusicians = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contributingBand", cascade = CascadeType.ALL)
    private List<MusicTable> contributedMusics = new ArrayList<>();

    @OneToMany(mappedBy = "band", fetch = FetchType.LAZY)
    private List<QuoteRequestTable> quoteRequests;

    public BandTable(BandRequest form, String ownerUuid) {
        this.active = true;
        this.creationDate = LocalDateTime.now();
        this.name = form.getName();
        this.description = form.getDescription();
        this.address = new BandAddressTable(form.getAddress());
        this.address.setBand(this);
        this.ownerUuid = ownerUuid;

        this.contacts = form.getContacts()
                .stream()
                .map(c -> new ContactTable(c, this))
                .collect(Collectors.toList());
    }

    @Override
    public void update(UpdateBandRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.address.update(request.getAddress());
        this.updateDate = LocalDateTime.now();
    }

    public void associate(MusicianTable musician) {
        var association = new BandMusicianTable(this, musician);
        this.associatedMusicians.add(association);
    }

    public boolean isAssociatedWith(MusicianTable musician) {
        return this.getAssociation(musician).isPresent();
    }

    public Optional<BandMusicianTable> getAssociation(MusicianTable musician) {
        return this.associatedMusicians
                .stream()
                .filter(assoc -> assoc.getMusician().getUuid().equals(musician.getUuid()))
                .findFirst();
    }

    public void removeProfilePicture() {
        this.profilePictureUuid = null;
    }

    public List<MusicianTable> getAllMusicians() {
        var musicians = this.associatedMusicians
                .stream()
                .map(
                        BandMusicianTable::getMusician
                ).collect(Collectors.toList());
        musicians.addAll(this.insertedMusicians);

        var auxMap = new HashMap<String, MusicianTable>();

        musicians.forEach(
                musician -> {
                    Optional.ofNullable(auxMap.get(musician.getUuid()))
                            .ifPresentOrElse(
                                    m -> {
                                    },
                                    () -> auxMap.put(musician.getUuid(), musician)
                            );
                }
        );

        return new ArrayList<>(auxMap.values());
    }
}
