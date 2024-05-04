package br.com.events.band.business.command.quote_request;

import br.com.events.band.adapter.messaging.sqs.sender.SqsMessageSender;
import br.com.events.band.data.io.quote_request.request.QuoteRequestCreationErrorMessage;
import br.com.events.band.data.io.quote_request.request.QuoteRequestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendQuoteCreationErrorMessageCommand {

    private final SqsMessageSender sqsMessageSender;

    @Value("${cloud.aws.endpoint.uri.quote.request.band-dlq}")
    private String quoteRequestCreationErrorMessageQueue;

    public void execute(QuoteRequestRequest request) {
        var message = new QuoteRequestCreationErrorMessage(request);
        sqsMessageSender.send(quoteRequestCreationErrorMessageQueue, message);
    }
}
