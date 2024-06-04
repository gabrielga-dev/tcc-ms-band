package br.com.events.band.business.use_case.quote;

import br.com.events.band.data.io.quote.message.QuoteEventAnswerMessage;

public interface HandleQuoteEventAnswerUseCase {

    void execute(QuoteEventAnswerMessage answer);
}
