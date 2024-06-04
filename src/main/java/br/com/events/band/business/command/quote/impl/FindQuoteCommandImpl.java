package br.com.events.band.business.command.quote.impl;

import br.com.events.band.adapter.repository.QuoteRepository;
import br.com.events.band.business.command.quote.FindQuoteCommand;
import br.com.events.band.core.exception.quote.QuoteNotFound;
import br.com.events.band.data.model.table.quote.QuoteTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindQuoteCommandImpl implements FindQuoteCommand {

    private final QuoteRepository quoteRepository;

    @Override
    public QuoteTable byUuidOrThrow(String quoteUuid) {
        return quoteRepository.findById(quoteUuid).orElseThrow(QuoteNotFound::new);
    }
}
