package br.com.events.band.business.use_case.quote.impl;

import br.com.events.band.business.command.quote.FindQuoteCommand;
import br.com.events.band.business.command.quote.SaveQuoteCommand;
import br.com.events.band.business.command.quote.SendQuoteAnswerEmailMessageCommand;
import br.com.events.band.business.use_case.quote.HandleQuoteEventAnswerUseCase;
import br.com.events.band.data.io.quote.message.QuoteEventAnswerMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class HandleQuoteEventAnswerUseCaseImpl implements HandleQuoteEventAnswerUseCase {

    private final FindQuoteCommand findQuoteCommand;
    private final SaveQuoteCommand saveQuoteCommand;
    private final SendQuoteAnswerEmailMessageCommand sendQuoteAnswerEmailMessageCommand;

    @Override
    @Transactional
    public void execute(QuoteEventAnswerMessage answer) {
        var quote = findQuoteCommand.byUuidOrThrow(answer.getQuoteUuid());

        quote.update(answer);
        saveQuoteCommand.execute(quote);

        sendQuoteAnswerEmailMessageCommand.execute(quote, answer.isHired());
    }
}
