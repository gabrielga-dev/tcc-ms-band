package br.com.events.band.business.command.quote_request;

import br.com.events.band.adapter.feign.MsAuthFeign;
import br.com.events.band.adapter.messaging.sqs.sender.SqsMessageSender;
import br.com.events.band.data.io.email.request.RawEmailRequest;
import br.com.events.band.data.io.event.response.EventProfileResponse;
import br.com.events.band.data.io.quote_request.request.QuoteRequestRequest;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendQuoteRequestEmailMessageCommand {

    private final MsAuthFeign msAuthFeign;
    private final SqsMessageSender sqsMessageSender;

    @Value("${cloud.aws.endpoint.uri.email-request}")
    private String emailMessageQueue;

    public void execute(QuoteRequestRequest request, EventProfileResponse event, BandTable bandTable){
        var bandOwner = msAuthFeign.findPersonInformationByUuid(bandTable.getOwnerUuid());
        var message = new RawEmailRequest(request, event, bandTable, bandOwner.getEmail());
        sqsMessageSender.send(emailMessageQueue, message);
    }
}
