package br.com.events.band.data.model.table.pk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class BandMusicianTablePk implements Serializable {

    private String bandUuid;

    private String musicianUuid;
}
