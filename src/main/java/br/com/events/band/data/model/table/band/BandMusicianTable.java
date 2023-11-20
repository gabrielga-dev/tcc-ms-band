package br.com.events.band.data.model.table.band;

import br.com.events.band.data.model.table.musician.MusicianTable;
import br.com.events.band.data.model.table.pk.BandMusicianTablePk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "band_musician")
public class BandMusicianTable {

    @EmbeddedId
    private BandMusicianTablePk pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bandUuid")
    private BandTable band;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("musicianUuid")
    private MusicianTable musician;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public BandMusicianTable(BandTable band, MusicianTable musician) {
        this.pk = new BandMusicianTablePk(band, musician);
        this.band = band;
        this.musician = musician;
        this.creationDate = LocalDateTime.now();
    }
}
