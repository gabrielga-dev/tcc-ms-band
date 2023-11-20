package br.com.events.band.data.model.table.musician;

import br.com.events.band.data.io.musician.request.MusicianRequest;
import br.com.events.band.data.model.ActionableTable;
import br.com.events.band.data.model.TableWithProfilePicture;
import br.com.events.band.data.model.UpdatableTable;
import br.com.events.band.data.model.table.band.BandMusicianTable;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * This class represents the musician's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "musician")
public class MusicianTable implements ActionableTable, UpdatableTable<MusicianRequest>, TableWithProfilePicture {

    @Id
    @Column(name = "uuid")
    private final String uuid = UUID.randomUUID().toString();

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthday")
    private LocalDateTime birthday;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_uuid")
    private BandTable bandThatInserted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "musician", cascade = CascadeType.ALL)
    private List<BandMusicianTable> bands;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "avatar_picture_uuid")
    private String profilePictureUuid;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "musician", cascade = CascadeType.ALL)
    private MusicianAddressTable address;

    @ManyToMany
    private List<MusicianTypeTable> types;

    public MusicianTable(MusicianRequest musicianForm) {
        this.firstName = musicianForm.getFirstName();
        this.lastName = musicianForm.getLastName();
        this.birthday = musicianForm.getBirthday();
        this.cpf = musicianForm.getCpf();
        this.email = musicianForm.getEmail();
        this.creationDate = LocalDateTime.now();

        var newAddress = new MusicianAddressTable(musicianForm.getAddress());
        newAddress.setMusician(this);
        this.address = newAddress;
    }

    @Override
    public void update(MusicianRequest data) {
        this.firstName = data.getFirstName();
        this.lastName = data.getLastName();
        this.birthday = data.getBirthday();
        this.cpf = data.getCpf();
        this.email = data.getEmail();
        this.creationDate = LocalDateTime.now();
        this.address.transferData(data.getAddress());
    }
}
