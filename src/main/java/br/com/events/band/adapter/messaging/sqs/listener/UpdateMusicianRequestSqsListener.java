package br.com.events.band.adapter.messaging.sqs.listener;


import br.com.events.band.business.use_case.musician.UpdateMusicianUseCase;
import br.com.events.band.data.io.musician.request.UpdateMusicianRequestMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateMusicianRequestSqsListener {

    private final ObjectMapper objectMapper;
    private final UpdateMusicianUseCase updateMusicianUseCase;

    @SqsListener("${cloud.aws.endpoint.uri.update.musician}")
    public void receiveUpdateMusicianRequestMessage(String message) {
        try {
            log.info("[START] Received musician update request!: {}", message);

            var request = objectMapper.readValue(message, UpdateMusicianRequestMessage.class);
            updateMusicianUseCase.execute(request);

            log.info("[END] Musician updated!");
        } catch (Exception ex) {
            log.info("[END] ERROR! Musician not updated! {}", ex.getMessage());
        }
    }
}
