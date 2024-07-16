package br.com.events.band.data.io.quote.message;

import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuoteEventAnswerMessageMock {

    public static QuoteEventAnswerMessage build() {
        return new QuoteEventAnswerMessage(MockConstants.STRING, MockConstants.BOOLEAN);
    }
}
