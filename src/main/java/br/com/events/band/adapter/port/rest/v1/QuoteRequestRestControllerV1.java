package br.com.events.band.adapter.port.rest.v1;

import br.com.events.band.adapter.port.QuoteRequestPort;
import br.com.events.band.business.use_case.quote_request.DeclineQuoteRequestUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/quote-request")
@RequiredArgsConstructor
public class QuoteRequestRestControllerV1 implements QuoteRequestPort {

    private final DeclineQuoteRequestUseCase declineQuoteRequestUseCase;

    @Override
    @DeleteMapping("/{quoteRequestUuid}")
    @PreAuthorize("hasAuthority('BAND')")
    public ResponseEntity<Void> decline(@PathVariable String quoteRequestUuid) {
        declineQuoteRequestUseCase.execute(quoteRequestUuid);
        return ResponseEntity.noContent().build();
    }
}
