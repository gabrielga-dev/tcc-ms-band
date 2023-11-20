package br.com.events.band.data.model.table.musician;

import br.com.events.band.data.model.table.musician.MusicianTable;
import br.com.events.band.data.model.table.quote_request.QuoteRequestMusicianTypeTable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "musician_type")
public class MusicianTypeTable {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany
    private List<MusicianTable> musicians;

    @OneToMany(mappedBy = "musicianType", fetch = FetchType.LAZY)
    private List<QuoteRequestMusicianTypeTable> quoteRequests;
}
