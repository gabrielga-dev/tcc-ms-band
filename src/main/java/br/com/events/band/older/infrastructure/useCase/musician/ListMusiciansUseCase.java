package br.com.events.band.older.infrastructure.useCase.musician;

import br.com.events.band.older.domain.io._new.musician.response.MusicianResponse;

import java.util.List;

public interface ListMusiciansUseCase {

    List<MusicianResponse> execute(String bandUuid);
}
