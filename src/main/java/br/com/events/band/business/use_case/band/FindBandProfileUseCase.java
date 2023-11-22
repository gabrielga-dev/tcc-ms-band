package br.com.events.band.business.use_case.band;

import br.com.events.band.data.io.band.response.BandProfileResponse;

public interface FindBandProfileUseCase {

    BandProfileResponse execute(String bandUuid);
}
