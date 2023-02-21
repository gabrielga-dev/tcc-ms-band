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

import br.com.events.band.domain.entity.type.ContactType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the contact's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_uuid")
    private Band band;

    @Column(name = "type", nullable = false)
    private ContactType type;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
