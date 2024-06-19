package br.com.events.band.adapter.messaging.sqs.listener;


import br.com.events.band.business.use_case.quote.HandleQuoteEventAnswerUseCase;
import br.com.events.band.data.io.quote.message.QuoteEventAnswerMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuoteEventAnswerSqsListener {

    private final ObjectMapper objectMapper;
    private final HandleQuoteEventAnswerUseCase handleQuoteEventAnswerUseCase;

    @SqsListener("${cloud.aws.endpoint.uri.quote.request.event.answered}")
    public void listen(String message) {
        try {
            log.info("[START] Received quote event answer!: {}", message);

            var request = objectMapper.readValue(message, QuoteEventAnswerMessage.class);
            handleQuoteEventAnswerUseCase.execute(request);

            log.info("[END] Quote answer handled!");
        } catch (Exception ex) {
            log.info("[END] ERROR! Quote answer not handled! {}", ex.getMessage());
        }
    }
}
