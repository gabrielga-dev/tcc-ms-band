package br.com.events.band.data.model.table.pk;

import br.com.events.band.data.model.table.band.BandTable;
import br.com.events.band.data.model.table.musician.MusicianTable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class BandMusicianTablePk implements Serializable {

    private String bandUuid;

    private String musicianUuid;

    public BandMusicianTablePk(BandTable band, MusicianTable musician) {
        this.bandUuid = band.getUuid();
        this.musicianUuid = musician.getUuid();
    }
}
