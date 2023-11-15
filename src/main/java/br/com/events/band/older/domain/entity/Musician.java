package br.com.events.band.older.domain.entity;

import br.com.events.band.newer.data.table.BandTable;
import br.com.events.band.older.domain.entity.address.MusicianAddress;
import br.com.events.band.older.domain.io._new.musician.form.MusicianForm;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
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
public class Musician {

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
    @JoinColumn(name = "band_uuid", nullable = false)
    private BandTable band;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "avatar_uuid")
    private String avatarUuid;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "musician", cascade = CascadeType.ALL)
    private MusicianAddress address;

    public Musician(MusicianForm musicianForm) {
        this.firstName = musicianForm.getFirstName();
        this.lastName = musicianForm.getLastName();
        this.birthday = musicianForm.getBirthday();
        this.cpf = musicianForm.getCpf();
        this.email = musicianForm.getEmail();
        this.creationDate = LocalDateTime.now();

        var newAddress = new MusicianAddress(musicianForm.getAddress());
        newAddress.setMusician(this);
        this.address = newAddress;
    }

    public void transferData(MusicianForm musicianForm){
        this.firstName = musicianForm.getFirstName();
        this.lastName = musicianForm.getLastName();
        this.birthday = musicianForm.getBirthday();
        this.cpf = musicianForm.getCpf();
        this.email = musicianForm.getEmail();
        this.creationDate = LocalDateTime.now();
        this.address.transferData(musicianForm.getAddress());
    }
}
