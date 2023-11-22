package br.com.events.band.data.io.musician_type.response;

import br.com.events.band.data.model.table.musician.MusicianTypeTable;
import lombok.Getter;

@Getter
public class MusicianTypeResponse {

    private final String uuid;
    private final String name;
    private final String description;

    public MusicianTypeResponse(MusicianTypeTable type) {
        this.uuid = type.getUuid();
        this.name = type.getName();
        this.description = type.getDescription();
    }
}
