package br.com.events.band.data.model.table.pk;

import br.com.events.band.data.model.table.band.BandTable;
import br.com.events.band.data.model.table.musician.MusicianTable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
@NoArgsConstructor
public class BandMusicianTablePk implements Serializable {

    @Column(name = "band_uuid")
    private String bandUuid;

    @Column(name = "musician_uuid")
    private String musicianUuid;

    public BandMusicianTablePk(BandTable band, MusicianTable musician) {
        this.bandUuid = band.getUuid();
        this.musicianUuid = musician.getUuid();
    }

    @Override
    public int hashCode() {
        return Objects.hash(bandUuid, bandUuid);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof BandMusicianTablePk)) {
            var other = (BandMusicianTablePk) obj;
            var bandEquals = this.bandUuid.equals(other.getBandUuid());
            var musicianEquals = this.musicianUuid.equals(other.getMusicianUuid());

            return bandEquals && musicianEquals;
        }
        return false;
    }
}
