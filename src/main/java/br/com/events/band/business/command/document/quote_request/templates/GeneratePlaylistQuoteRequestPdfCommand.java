package br.com.events.band.business.command.document.quote_request.templates;

import br.com.events.band.adapter.feign.MsEventFeign;
import br.com.events.band.business.command.document.quote_request.GenerateQuoteRequestPdfCommand;
import br.com.events.band.core.util.DateUtil;
import br.com.events.band.data.io.document.PdfConfigurationDTO;
import br.com.events.band.data.io.document.quote_request.playlist.MusicDocumentDTO;
import br.com.events.band.data.io.pdf.PdfDTO;
import br.com.events.band.data.io.pdf.PdfType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GeneratePlaylistQuoteRequestPdfCommand extends GenerateQuoteRequestPdfCommand {

    private final MsEventFeign msEventFeign;

    @Override
    public PdfType getAcceptedPdfType() {
        return PdfType.PLAYLIST;
    }

    @Override
    public PdfDTO execute(String quoteRequestUuid) {
        var template = findTemplate();
        var configuration = new PdfConfigurationDTO(template.getContent());
        var params = this.generateParams(quoteRequestUuid);

        return new PdfDTO(
                "playlist.pdf",
                generatePdfDocumentCommand.execute(configuration, params)
        );
    }

    private Map<String, Object> generateParams(String quoteRequestUuid) {
        var quoteRequest = findQuoteRequest(quoteRequestUuid);
        var event = msEventFeign.findProfile(quoteRequest.getEventUuid());

        var musics = quoteRequest.getWantedMusics()
                .stream()
                .map(MusicDocumentDTO::new)
                .sorted(Comparator.comparing(MusicDocumentDTO::getOrderNumber))
                .collect(Collectors.toList());

        return Map.of(
                "bandName", quoteRequest.getBand().getName(),
                "eventName", event.getName(),
                "musics", musics,
                "generatedAt", DateUtil.formatDate(LocalDateTime.now())
        );
    }
}
