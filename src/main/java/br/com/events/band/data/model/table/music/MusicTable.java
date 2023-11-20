package br.com.events.band.data.model.table.music;

import br.com.events.band.data.io.music.request.MusicRequest;
import br.com.events.band.data.model.ActionableTable;
import br.com.events.band.data.model.UpdatableTable;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This class represents the music's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "music")
public class MusicTable implements ActionableTable, UpdatableTable<MusicRequest> {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_uuid")
    private BandTable contributingBand;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "artist", nullable = false)
    private String artist;

    @Column(name = "observation")
    private String observation;

    @Column(name = "active")
    private Boolean active = Boolean.TRUE;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    public MusicTable(MusicRequest request, BandTable band) {
        this.name = request.getName();
        this.author = request.getAuthor();
        this.artist = request.getArtist();
        this.observation = request.getObservation();
        this.creationDate = LocalDateTime.now();
        this.contributingBand = band;
    }

    @Override
    public void update(MusicRequest request) {
        this.name = request.getName();
        this.author = request.getAuthor();
        this.artist = request.getArtist();
        this.observation = request.getObservation();
        this.updateDate = LocalDateTime.now();
    }
}
