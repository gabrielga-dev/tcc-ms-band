package br.com.events.band.business.use_case.band;

import br.com.events.band.data.io.musician.response.MusicianResponse;

import java.util.List;

public interface FindAllMusiciansUseCase {

    List<MusicianResponse> execute(String bandUuid);
}
