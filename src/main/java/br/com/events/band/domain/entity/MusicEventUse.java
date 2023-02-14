package br.com.events.band.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the music usage's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "music_event_use")
public class MusicEventUse {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "event_uuid", nullable = false)
    private String eventUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_uuid")
    private Music music;

    @Column(name = "play_order", nullable = false)
    private Integer playOrder;

    @Column(name = "play_when")
    private String playWhen;

    @Column(name = "observation")
    private String observation;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
