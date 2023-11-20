package br.com.events.band.older.domain.entity;

import br.com.events.band.newer.data.model.table.MusicTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This class represents the quote music's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quote")
public class QuoteMusic {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_uuid", nullable = false)
    private Quote quote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_uuid", nullable = false)
    private MusicTable music;

    @Column(name = "play_order", nullable = false)
    private Integer playOrder;

    @Column(name = "play_when", nullable = false)
    private String playWhen;

    @Column(name = "observation")
    private String observation;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;
}
