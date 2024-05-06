package br.com.events.band.data.model.table.quote_request;

import br.com.events.band.data.io.quote_request.request.MusicQuoteRequestRequest;
import br.com.events.band.data.model.table.music.MusicTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "quote_request_music")
@NoArgsConstructor
public class QuoteRequestMusicTable {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_request_uuid")
    private QuoteRequestTable quoteRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_uuid")
    private MusicTable music;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "observation")
    private String observation;

    public QuoteRequestMusicTable(
            MusicQuoteRequestRequest musicRequest,
            MusicTable musicTable,
            QuoteRequestTable quoteRequestTable
    ) {
        this.quoteRequest = quoteRequestTable;
        this.music = musicTable;
        this.orderNumber = musicRequest.getOrder();
        this.observation = musicRequest.getObservation();
    }
}
