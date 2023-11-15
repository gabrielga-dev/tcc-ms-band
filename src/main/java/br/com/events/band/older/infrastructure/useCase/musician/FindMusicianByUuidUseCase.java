package br.com.events.band.older.infrastructure.useCase.musician;

import br.com.events.band.older.domain.io._new.musician.response.MusicianResponse;

public interface FindMusicianByUuidUseCase {

    MusicianResponse execute(String bandUuid, String musicianUuid);
}
