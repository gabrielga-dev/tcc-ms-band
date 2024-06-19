package br.com.events.band.business.command.quote.impl;

import br.com.events.band.adapter.repository.QuoteRepository;
import br.com.events.band.business.command.quote.SaveQuoteCommand;
import br.com.events.band.data.model.table.quote.QuoteTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveQuoteCommandImpl implements SaveQuoteCommand {

    private final QuoteRepository quoteRepository;

    @Override
    public QuoteTable execute(QuoteTable quote) {
        return quoteRepository.save(quote);
    }
}
