package br.com.events.band.business.use_case.band;

import br.com.events.band.data.io.band.criteria.FindBandsCriteria;
import br.com.events.band.data.io.band.response.BandResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindBandsUseCase {

    Page<BandResponse> execute(FindBandsCriteria filters, Pageable pageable);
}
