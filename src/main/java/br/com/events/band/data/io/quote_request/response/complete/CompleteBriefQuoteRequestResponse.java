package br.com.events.band.data.io.quote_request.response.complete;

import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CompleteBriefQuoteRequestResponse {

    private String eventUuid;
    private String description;
    private List<WantedMusicsQuoteRequestResponse> wantedMusics;
    private List<WantedMusicianTypeQuoteRequestResponse> wantedMusicianTypes;

    public CompleteBriefQuoteRequestResponse(QuoteRequestTable quoteRequest) {
        this.eventUuid = quoteRequest.getEventUuid();
        this.description = quoteRequest.getDescription();
        this.wantedMusics = quoteRequest.getWantedMusics()
                .stream()
                .map(WantedMusicsQuoteRequestResponse::new)
                .sorted(Comparator.comparing(WantedMusicsQuoteRequestResponse::getOrder))
                .collect(Collectors.toList());
        this.wantedMusicianTypes = quoteRequest.getWantedMusicianTypes()
                .stream()
                .map(WantedMusicianTypeQuoteRequestResponse::new)
                .collect(Collectors.toList());
    }
}
