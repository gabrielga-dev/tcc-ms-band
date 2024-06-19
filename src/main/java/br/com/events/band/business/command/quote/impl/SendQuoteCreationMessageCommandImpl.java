package br.com.events.band.business.command.quote.impl;

import br.com.events.band.adapter.messaging.sqs.sender.SqsMessageSender;
import br.com.events.band.business.command.quote.SendQuoteCreationMessageCommand;
import br.com.events.band.data.io.quote.message.QuoteCreatedMessage;
import br.com.events.band.data.model.table.quote.QuoteTable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendQuoteCreationMessageCommandImpl implements SendQuoteCreationMessageCommand {

    private final SqsMessageSender sqsMessageSender;

    @Value("${cloud.aws.endpoint.uri.quote.request.accepted}")
    private String queue;


    @Override
    public void execute(QuoteTable quote) {
        var message = new QuoteCreatedMessage(quote);
        sqsMessageSender.send(queue, message);
    }
}
