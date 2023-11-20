package br.com.events.band.newer.business.use_case.musician;

import br.com.events.band.newer.data.io.commom.UuidHolderDTO;

public interface AssociateCreatedMusicianUseCase {

    UuidHolderDTO execute(String bandUuid, String musicianCpf);
}
