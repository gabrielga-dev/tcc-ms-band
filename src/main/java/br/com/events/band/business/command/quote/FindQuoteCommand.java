package br.com.events.band.business.command.quote;

import br.com.events.band.data.model.table.quote.QuoteTable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface FindQuoteCommand {

    QuoteTable byUuidOrThrow(String quoteUuid);

    List<QuoteTable> bySpecification(Specification<QuoteTable> specification);
}
