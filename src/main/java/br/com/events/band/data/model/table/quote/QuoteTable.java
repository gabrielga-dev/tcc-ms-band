package br.com.events.band.data.model.table.quote;

import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "quote")
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_request_uuid", referencedColumnName = "uuid")
    private QuoteRequestTable quoteRequest;
}
