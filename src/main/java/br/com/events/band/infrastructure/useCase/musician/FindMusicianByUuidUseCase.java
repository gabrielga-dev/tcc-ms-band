package br.com.events.band.infrastructure.useCase.musician;

import br.com.events.band.domain.io.musician.list.useCase.out.ListMusiciansUseCaseResult;

public interface FindMusicianByUuidUseCase {

    ListMusiciansUseCaseResult execute(String bandUuid, String musicianUuid);
}
