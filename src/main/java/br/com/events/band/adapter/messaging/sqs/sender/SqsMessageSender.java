package br.com.events.band.adapter.messaging.sqs.sender;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SqsMessageSender {

    private final QueueMessagingTemplate messagingTemplate;

    private final Gson gson;

    public <T> void send(String endpoint, T body){
        var objectJson = gson.toJson(body);
        var message = MessageBuilder.withPayload(objectJson).build();
        messagingTemplate.send(endpoint, message);
    }
}
