package br.com.events.band.adapter.messaging.sqs.listener;


import br.com.events.band.business.use_case.quote_request.CreateQuoteRequestUseCase;
import br.com.events.band.data.io.quote_request.request.QuoteRequestRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateQuoteRequestSqsListener {

    private final ObjectMapper objectMapper;
    private final CreateQuoteRequestUseCase updateMusicianUseCase;

    @SqsListener("${cloud.aws.endpoint.uri.quote.request.band}")
    public void receiveUpdateMusicianRequestMessage(String message) {
        try {
            log.info("[START] Received create quote request update request!: {}", message);

            var request = objectMapper.readValue(message, QuoteRequestRequest.class);
            updateMusicianUseCase.execute(request);

            log.info("[END] Quote request created!");
        } catch (Exception ex) {
            log.info("[END] ERROR! Quote request not created! {}", ex.getMessage());
        }
    }
}
