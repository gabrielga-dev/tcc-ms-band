package br.com.events.band.adapter.feign.client;

import br.com.events.band.adapter.feign.MsEventFeign;
import br.com.events.band.adapter.feign.client.config.MyEventFeignClientConfiguration;
import br.com.events.band.data.io.event.response.EventProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "event-ms-event",
        url = "${feign.client.ms.event.url}",
        configuration = MyEventFeignClientConfiguration.class
)
public interface MsEventFeignClient extends MsEventFeign {

    @GetMapping("/v1/event/{uuid}/profile")
    EventProfileResponse findProfile(@PathVariable("uuid") String eventUuid);
}
