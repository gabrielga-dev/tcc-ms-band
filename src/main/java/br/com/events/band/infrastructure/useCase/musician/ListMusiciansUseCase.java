package br.com.events.band.infrastructure.useCase.musician;

import br.com.events.band.domain.io._new.musician.response.MusicianResponse;

import java.util.List;

public interface ListMusiciansUseCase {

    List<MusicianResponse> execute(String bandUuid);
}
