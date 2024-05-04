package br.com.events.band.data.io.quote_request.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class QuoteRequestRequest implements Serializable {

    private String eventUuid;
    private String quoteRequestUuid;
    private String bandUuid;
    private String description;
    private List<MusicQuoteRequestRequest> playlist = new ArrayList<>();
    private List<MusicianTypeQuoteRequestRequest> musicianTypes = new ArrayList<>();

    @JsonIgnore
    public List<String> getMusicUuids() {
        return this.playlist.stream()
                .map(MusicQuoteRequestRequest::getMusicUuid)
                .collect(Collectors.toList());
    }

    @JsonIgnore
    public List<String> getMusicianTypeUuids() {
        return this.musicianTypes.stream()
                .map(MusicianTypeQuoteRequestRequest::getMusicianTypeUuid)
                .collect(Collectors.toList());
    }
}
