package br.com.events.band.business.command.quote_request;

import br.com.events.band.adapter.repository.QuoteRequestRepository;
import br.com.events.band.data.io.quote_request.criteria.FindQuoteRequestCriteria;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBandsQuoteRequestsCommand {

    private final QuoteRequestRepository quoteRequestRepository;

    public Page<QuoteRequestTable> execute(String bandUuid, FindQuoteRequestCriteria criteria, Pageable pageable) {
        return quoteRequestRepository.findByBandUuid(
                bandUuid,
                criteria.getStatuses().isEmpty()
                        ? null
                        : criteria.getStatuses(),
                criteria.getStartDate(),
                criteria.getEndDate(),
                pageable
        );
    }
}
