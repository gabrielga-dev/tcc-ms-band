package br.com.events.band.newer.data.table.pk;

import lombok.Data;

import java.io.Serializable;

@Data
public class BandMusicianTablePk implements Serializable {

    private String bandUuid;
    private String musicianUuid;
}
