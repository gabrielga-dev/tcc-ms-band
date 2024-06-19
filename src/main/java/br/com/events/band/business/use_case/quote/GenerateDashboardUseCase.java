package br.com.events.band.business.use_case.quote;

import br.com.events.band.data.io.quote.request.DashboardRequest;
import br.com.events.band.data.io.quote.response.DashboardResponse;

public interface GenerateDashboardUseCase {

    DashboardResponse execute(DashboardRequest criteria);
}
