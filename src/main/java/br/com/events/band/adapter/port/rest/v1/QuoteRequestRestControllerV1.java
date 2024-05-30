package br.com.events.band.adapter.port.rest.v1;

import br.com.events.band.adapter.port.QuoteRequestPort;
import br.com.events.band.business.use_case.quote_request.AcceptQuoteRequestUseCase;
import br.com.events.band.business.use_case.quote_request.DeclineQuoteRequestUseCase;
import br.com.events.band.business.use_case.quote_request.FindQuoteRequestByUuidUseCase;
import br.com.events.band.business.use_case.quote_request.GenerateQuoteRequestPdfUseCase;
import br.com.events.band.core.util.FileUtil;
import br.com.events.band.data.io.pdf.PdfType;
import br.com.events.band.data.io.quote_request.request.AcceptQuoteRequestRequest;
import br.com.events.band.data.io.quote_request.response.complete.CompleteBriefQuoteRequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/quote-request")
@RequiredArgsConstructor
public class QuoteRequestRestControllerV1 implements QuoteRequestPort {

    private final DeclineQuoteRequestUseCase declineQuoteRequestUseCase;
    private final FindQuoteRequestByUuidUseCase findQuoteRequestByUuidUseCase;
    private final GenerateQuoteRequestPdfUseCase generateQuoteRequestPdfUseCase;
    private final AcceptQuoteRequestUseCase acceptQuoteRequestUseCase;

    @Override
    @GetMapping("/{quoteRequestUuid}")
    @PreAuthorize("hasAuthority('BAND')")
    public ResponseEntity<CompleteBriefQuoteRequestResponse> findByUuid(
            @PathVariable("quoteRequestUuid") String quoteRequestUuid
    ) {
        var quoteRequest = findQuoteRequestByUuidUseCase.execute(quoteRequestUuid);
        return ResponseEntity.ok(quoteRequest);
    }

    @Override
    @DeleteMapping("/{quoteRequestUuid}")
    @PreAuthorize("hasAuthority('BAND')")
    public ResponseEntity<Void> decline(@PathVariable String quoteRequestUuid) {
        declineQuoteRequestUseCase.execute(quoteRequestUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping("/{quoteRequestUuid}")
    @PreAuthorize("hasAuthority('BAND')")
    public ResponseEntity<Void> accept(
            @PathVariable String quoteRequestUuid,
            @RequestBody @Valid AcceptQuoteRequestRequest request
    ) {
        acceptQuoteRequestUseCase.execute(quoteRequestUuid, request);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{quoteRequestUuid}/playlist")
    public ResponseEntity<InputStreamResource> downloadPlaylist(@PathVariable String quoteRequestUuid) {
        var pdf = generateQuoteRequestPdfUseCase.execute(quoteRequestUuid, PdfType.PLAYLIST);
        return FileUtil.output(pdf.getFileBytes(), pdf.getFileName());
    }
}
