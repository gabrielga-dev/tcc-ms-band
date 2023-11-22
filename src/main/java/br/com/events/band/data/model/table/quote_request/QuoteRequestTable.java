package br.com.events.band.data.model.table.quote_request;

import br.com.events.band.data.model.table.band.BandTable;
import br.com.events.band.data.model.table.quote.QuoteTable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "quote_request")
public class QuoteRequestTable {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "event_uuid", nullable = false)
    private String eventUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private BandTable band;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "quoteRequest", fetch = FetchType.LAZY)
    private List<QuoteRequestMusicTable> wantedMusics;

    @OneToMany(mappedBy = "quoteRequest", fetch = FetchType.LAZY)
    private List<QuoteRequestMusicianTypeTable> wantedMusicianTypes;

    @OneToMany(mappedBy = "quoteRequest", fetch = FetchType.LAZY)
    private List<QuoteTable> quotes;
}
