package br.com.events.band.business.command.quote_request;

import br.com.events.band.adapter.repository.QuoteRequestRepository;
import br.com.events.band.core.exception.quote_request.QuoteRequestNotFound;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindQuoteRequestCommand {

    private final QuoteRequestRepository quoteRequestRepository;

    public QuoteRequestTable findByUuidOrThrow(String quoteRequestUuid) {
        return quoteRequestRepository.findById(quoteRequestUuid)
                .orElseThrow(QuoteRequestNotFound::new);
    }
}
