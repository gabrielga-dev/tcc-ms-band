package br.com.events.band.data.model.table.quote;

import br.com.events.band.data.io.quote.message.QuoteEventAnswerMessage;
import br.com.events.band.data.io.quote_request.request.AcceptQuoteRequestRequest;
import br.com.events.band.data.model.table.musician.MusicianTable;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "quote")
@NoArgsConstructor
public class QuoteTable {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private QuoteStatusType status;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "observation", nullable = false)
    private String observation;

    @ManyToMany
    @JoinTable(
            name = "quote_musician",
            joinColumns = @JoinColumn(name = "quote_uuid"),
            inverseJoinColumns = @JoinColumn(name = "musician_uuid"))
    private List<MusicianTable> hiredMusicians;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_request_uuid", referencedColumnName = "uuid")
    private QuoteRequestTable quoteRequest;

    public QuoteTable(
            AcceptQuoteRequestRequest request,
            List<MusicianTable> musicians,
            QuoteRequestTable quoteRequest
    ) {
        this.status = QuoteStatusType.NON_ANSWERED;
        this.price = request.getFinalValue();
        this.observation = request.getObservation();
        this.hiredMusicians = musicians;
        this.quoteRequest = quoteRequest;
    }

    public void update(QuoteEventAnswerMessage answer) {
        if (answer.isHired()){
            this.status = QuoteStatusType.HIRED;
        } else {
            this.status = QuoteStatusType.DECLINED;
        }
    }
}
