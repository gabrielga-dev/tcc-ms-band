package br.com.events.band.data.io.music.response;


import br.com.events.band.core.util.DateUtil;
import br.com.events.band.data.model.table.music.MusicTable;
import lombok.Getter;

@Getter
public class MusicResponse {

    private final String uuid;
    private final String name;
    private final String observation;
    private final Long creationDateTimestamp;

    public MusicResponse(MusicTable music) {
        this.uuid = music.getUuid();
        this.name = music.getName();
        this.observation = music.getObservation();
        this.creationDateTimestamp = DateUtil.localDateTimeToMilliseconds(music.getCreationDate());
    }
}
