package br.com.events.band.data.io.document.quote_request.playlist;

import br.com.events.band.data.model.table.quote_request.QuoteRequestMusicTable;
import lombok.Getter;

@Getter
public class MusicDocumentDTO {

    private final Integer orderNumber;
    private final String musicName;
    private final String musicAuthor;
    private final String musicArtist;
    private final String observation;

    public MusicDocumentDTO(QuoteRequestMusicTable wantedMusic) {
        this.orderNumber = wantedMusic.getOrderNumber();
        this.musicName = wantedMusic.getMusic().getName();
        this.musicAuthor = wantedMusic.getMusic().getAuthor();
        this.musicArtist = wantedMusic.getMusic().getArtist();
        this.observation = wantedMusic.getObservation();
    }
}
