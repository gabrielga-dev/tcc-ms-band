package br.com.events.band.infrastructure.useCase.musician;

import br.com.events.band.domain.io._new.musician.response.MusicianResponse;

public interface FindMusicianByUuidUseCase {

    MusicianResponse execute(String bandUuid, String musicianUuid);
}
