package br.com.events.band.adapter.feign;

import br.com.events.band.data.io.event.response.EventProfileResponse;
import br.com.events.band.data.io.quote_request.request.DeclineQuoteRequestMsEventRequest;

public interface MsEventFeign {

    EventProfileResponse findProfile(String eventUuid);

    void declineQuoteRequest(String quoteRequestUuid, DeclineQuoteRequestMsEventRequest declineQuoteRequestRequest);


}
