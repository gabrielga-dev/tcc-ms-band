package br.com.events.band.data.io.quote.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuoteEventAnswerMessage {

    private String quoteUuid;
    private boolean hired;
}
