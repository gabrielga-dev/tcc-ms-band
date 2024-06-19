package br.com.events.band.data.io.quote_request.response.complete;

import br.com.events.band.data.model.table.quote_request.QuoteRequestMusicianTypeTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WantedMusicianTypeQuoteRequestResponse {

    private String musicianTypeUuid;
    private String musicianTypeName;
    private Integer quantity;
    private String observation;

    public WantedMusicianTypeQuoteRequestResponse(QuoteRequestMusicianTypeTable wantedMusicianType) {
        this.musicianTypeUuid = wantedMusicianType.getMusicianType().getUuid();
        this.musicianTypeName = wantedMusicianType.getMusicianType().getName();
        this.quantity = wantedMusicianType.getQuantity();
        this.observation = wantedMusicianType.getObservation();
    }
}
