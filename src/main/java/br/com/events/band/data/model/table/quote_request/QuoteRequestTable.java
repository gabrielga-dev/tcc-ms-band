package br.com.events.band.data.model.table.quote_request;

import br.com.events.band.data.io.quote_request.request.QuoteRequestRequest;
import br.com.events.band.data.model.table.band.BandTable;
import br.com.events.band.data.model.table.music.MusicTable;
import br.com.events.band.data.model.table.musician.MusicianTypeTable;
import br.com.events.band.data.model.table.quote.QuoteTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "quote_request")
@NoArgsConstructor
public class QuoteRequestTable {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "event_uuid", nullable = false)
    private String eventUuid;

    @Column(name = "origin_quote_request_uuid", nullable = false)
    private String originQuoteRequestUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_uuid")
    private BandTable band;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuoteRequestStatusType status;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "quoteRequest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuoteRequestMusicTable> wantedMusics;

    @OneToMany(mappedBy = "quoteRequest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuoteRequestMusicianTypeTable> wantedMusicianTypes;

    @OneToOne(mappedBy = "quoteRequest")
    private QuoteTable quote;


    public QuoteRequestTable(
            QuoteRequestRequest request,
            Map<String, MusicTable> musicsMap,
            Map<String, MusicianTypeTable> musicianTypesMap,
            BandTable band
    ) {
        this.originQuoteRequestUuid = request.getQuoteRequestUuid();
        this.eventUuid = request.getEventUuid();
        this.band = band;
        this.status = QuoteRequestStatusType.NON_ANSWERED;
        this.description = request.getDescription();
        this.creationDate = LocalDateTime.now();
        this.wantedMusics = request.getPlaylist()
                .stream()
                .map(
                        music -> new QuoteRequestMusicTable(
                                music,
                                musicsMap.get(music.getMusicUuid()),
                                this
                        )
                ).collect(Collectors.toList());

        this.wantedMusicianTypes = request.getMusicianTypes()
                .stream()
                .map(
                        musicianType -> new QuoteRequestMusicianTypeTable(
                                musicianType,
                                musicianTypesMap.get(musicianType.getMusicianTypeUuid()),
                                this
                        )
                ).collect(Collectors.toList());
    }
}
