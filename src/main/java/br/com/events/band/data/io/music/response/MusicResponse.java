package br.com.events.band.data.io.music.response;


import br.com.events.band.core.util.DateUtil;
import br.com.events.band.data.model.table.music.MusicTable;
import lombok.Getter;

@Getter
public class MusicResponse {

    private final String uuid;
    private final String name;
    private final String author;
    private final String artist;
    private final String observation;
    private final Long creationDateTimestamp;
    private final boolean active;

    public MusicResponse(MusicTable music) {
        this.uuid = music.getUuid();
        this.name = music.getName();
        this.author = music.getAuthor();
        this.artist = music.getArtist();
        this.observation = music.getObservation();
        this.creationDateTimestamp = DateUtil.localDateTimeToMilliseconds(music.getCreationDate());
        this.active = music.isActive();
    }
}
