package br.com.events.band.data.model.table.band.contact;

import br.com.events.band.data.io.contact.request.ContactRequest;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.AllArgsConstructor;
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
 * This class represents the contact's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact")
public class ContactTable {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_uuid")
    private BandTable band;

    @Column(name = "type", nullable = false)
    private ContactType type;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    public ContactTable(ContactRequest request, BandTable band) {
        this.type = request.getType();
        this.content = request.getContent();
        this.creationDate = LocalDateTime.now();
        this.setBand(band);
    }

    public void update(ContactRequest request) {
        this.type = request.getType();
        this.content = request.getContent();
        this.updateDate = LocalDateTime.now();
    }
}
