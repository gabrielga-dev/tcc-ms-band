package br.com.events.band.newer.business.use_case.band;

import br.com.events.band.newer.data.io.band.criteria.AuthenticatedPersonBandsCriteria;
import br.com.events.band.newer.data.io.band.response.BandResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAuthenticatedPersonBandsUseCase {

    Page<BandResponse> execute(AuthenticatedPersonBandsCriteria filters, Pageable pageable);
}
