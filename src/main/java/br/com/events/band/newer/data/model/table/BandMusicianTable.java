package br.com.events.band.newer.data.model.table;

import br.com.events.band.newer.data.model.table.pk.BandMusicianTablePk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "band_musician")
@IdClass(BandMusicianTablePk.class)
public class BandMusicianTable {

    @Id
    @ManyToOne
    @JoinColumn(name = "band_uuid", referencedColumnName = "uuid")
    private BandTable band;

    @Id
    @ManyToOne
    @JoinColumn(name = "musician_uuid", referencedColumnName = "uuid")
    private MusicianTable musician;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public BandMusicianTable(BandTable band, MusicianTable musician) {
        this.band = band;
        this.musician = musician;
        this.creationDate = LocalDateTime.now();
    }
}
