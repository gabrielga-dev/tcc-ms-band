package br.com.events.band.data.io.quote_request.response.complete;

import br.com.events.band.data.model.table.quote_request.QuoteRequestMusicTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WantedMusicsQuoteRequestResponse implements Serializable {

    private String musicUuid;
    private String musicName;
    private String musicAuthor;
    private String musicArtist;
    private String observation;
    private Integer order;

    public WantedMusicsQuoteRequestResponse(QuoteRequestMusicTable wantedMusic) {
        this.musicUuid = wantedMusic.getMusic().getUuid();
        this.musicName = wantedMusic.getMusic().getName();
        this.musicAuthor = wantedMusic.getMusic().getAuthor();
        this.musicArtist = wantedMusic.getMusic().getArtist();
        this.observation = wantedMusic.getObservation();
        this.order = wantedMusic.getOrderNumber();
    }
}
