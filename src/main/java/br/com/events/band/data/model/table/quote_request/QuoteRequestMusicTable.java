package br.com.events.band.data.model.table.quote_request;

import br.com.events.band.data.model.table.music.MusicTable;
import lombok.Getter;
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
@Table(name = "quote_request_music")
public class QuoteRequestMusicTable {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    private QuoteRequestTable quoteRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    private MusicTable musicTable;
}
