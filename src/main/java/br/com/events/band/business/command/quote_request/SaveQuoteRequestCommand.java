package br.com.events.band.business.command.quote_request;

import br.com.events.band.adapter.repository.QuoteRequestRepository;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveQuoteRequestCommand {

    private final QuoteRequestRepository quoteRequestRepository;

    public QuoteRequestTable execute(QuoteRequestTable newQuoteRequest) {
        return this.quoteRequestRepository.save(newQuoteRequest);
    }
}
