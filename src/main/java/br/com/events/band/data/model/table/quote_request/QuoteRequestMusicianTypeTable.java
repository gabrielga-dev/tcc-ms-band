package br.com.events.band.data.model.table.quote_request;

import br.com.events.band.data.io.quote_request.request.MusicianTypeQuoteRequestRequest;
import br.com.events.band.data.model.table.musician.MusicianTypeTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "quote_request_musician_type")
@NoArgsConstructor
public class QuoteRequestMusicianTypeTable {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "observation")
    private String observation;

    @ManyToOne(fetch = FetchType.LAZY)
    private QuoteRequestTable quoteRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    private MusicianTypeTable musicianType;

    public QuoteRequestMusicianTypeTable(
            MusicianTypeQuoteRequestRequest musicianTypeRequest,
            MusicianTypeTable musicianTypeTable,
            QuoteRequestTable quoteRequestTable
    ) {
        this.quantity = musicianTypeRequest.getQuantity();
        this.observation = musicianTypeRequest.getObservation();
        this.musicianType = musicianTypeTable;
        this.quoteRequest = quoteRequestTable;
    }
}
