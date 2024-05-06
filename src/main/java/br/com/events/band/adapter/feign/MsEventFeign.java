package br.com.events.band.adapter.feign;

import br.com.events.band.data.io.event.response.EventProfileResponse;

public interface MsEventFeign {

    EventProfileResponse findProfile(String eventUuid);
}
