package br.com.events.band.business.command.quote.impl;

import br.com.events.band.adapter.feign.MsAuthFeign;
import br.com.events.band.adapter.feign.MsEventFeign;
import br.com.events.band.adapter.messaging.sqs.sender.SqsMessageSender;
import br.com.events.band.business.command.quote.SendQuoteAnswerEmailMessageCommand;
import br.com.events.band.data.io.email.request.RawEmailRequest;
import br.com.events.band.data.model.table.quote.QuoteTable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendQuoteAnswerEmailMessageCommandImpl implements SendQuoteAnswerEmailMessageCommand {

    private final MsAuthFeign msAuthFeign;
    private final MsEventFeign msEventFeign;
    private final SqsMessageSender sqsMessageSender;

    @Value("${cloud.aws.endpoint.uri.email-request}")
    private String emailMessageQueue;

    @Override
    public void execute(QuoteTable quote, boolean hired) {
        var event = msEventFeign.findProfile(quote.getQuoteRequest().getEventUuid());
        var bandOwner = msAuthFeign.findPersonInformationByUuid(quote.getQuoteRequest().getBand().getOwnerUuid());
        var message = new RawEmailRequest(quote, event, bandOwner.getEmail(), hired);
        sqsMessageSender.send(emailMessageQueue, message);
    }
}
