package br.com.events.band.newer.business.use_case.band;

import br.com.events.band.newer.data.io.band.response.BandProfileResponse;

public interface FindBandProfileUseCase {

    BandProfileResponse execute(String bandUuid);
}
