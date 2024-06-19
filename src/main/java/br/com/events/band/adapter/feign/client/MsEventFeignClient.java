package br.com.events.band.adapter.feign.client;

import br.com.events.band.adapter.feign.MsEventFeign;
import br.com.events.band.adapter.feign.client.config.MyEventFeignClientConfiguration;
import br.com.events.band.data.io.event.response.EventProfileResponse;
import br.com.events.band.data.io.quote_request.request.DeclineQuoteRequestMsEventRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "event-ms-event",
        url = "${feign.client.ms.event.url}",
        configuration = MyEventFeignClientConfiguration.class
)
public interface MsEventFeignClient extends MsEventFeign {

    @GetMapping("/v1/event/{uuid}/profile")
    EventProfileResponse findProfile(@PathVariable("uuid") String eventUuid);

    @PutMapping("/v1/quote-request/{uuid}/decline")
    void declineQuoteRequest(
            @PathVariable("uuid") String quoteRequestUuid,
            @RequestBody DeclineQuoteRequestMsEventRequest declineQuoteRequestRequest
    );
}
