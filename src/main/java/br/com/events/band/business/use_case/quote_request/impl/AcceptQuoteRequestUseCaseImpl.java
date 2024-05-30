package br.com.events.band.business.use_case.quote_request.impl;

import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.command.quote.SaveQuoteCommand;
import br.com.events.band.business.command.quote.SendQuoteCreationMessageCommand;
import br.com.events.band.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.band.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.band.business.use_case.quote_request.AcceptQuoteRequestUseCase;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.data.io.quote_request.request.AcceptQuoteRequestRequest;
import br.com.events.band.data.model.table.quote.QuoteTable;
import br.com.events.band.data.model.table.quote_request.QuoteRequestStatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AcceptQuoteRequestUseCaseImpl implements AcceptQuoteRequestUseCase {

    private final FindQuoteRequestCommand findQuoteRequestCommand;
    private final FindMusicianCommand findMusicianCommand;
    private final SaveQuoteCommand saveQuoteCommand;
    private final SaveQuoteRequestCommand saveQuoteRequestCommand;
    private final SendQuoteCreationMessageCommand sendQuoteCreationMessageCommand;

    @Override
    @Transactional
    public void execute(String quoteRequestUuid, AcceptQuoteRequestRequest request) {
        var quoteRequest = findQuoteRequestCommand.findByUuidOrThrow(quoteRequestUuid);
        if (!Objects.equals(quoteRequest.getBand().getOwnerUuid(), AuthUtil.getAuthenticatedPersonUuid())) {
            throw new BandOwnerException();
        }

        var musicians = findMusicianCommand.byUuids(request.getMusicianUuids());

        if (musicians.isEmpty() || musicians.stream().anyMatch(musician -> !musician.isActive())) {
            throw new MusicianDoesNotExistException();
        }

        var quote = new QuoteTable(request, musicians, quoteRequest);
        quote = saveQuoteCommand.execute(quote);

        quoteRequest.setStatus(QuoteRequestStatusType.ANSWERED);
        quoteRequest.setQuote(quote);
        saveQuoteRequestCommand.execute(quoteRequest);

        sendQuoteCreationMessageCommand.execute(quote);
    }
}
