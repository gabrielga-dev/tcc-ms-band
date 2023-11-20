package br.com.events.band.business.use_case.musician;

import br.com.events.band.data.io.commom.UuidHolderDTO;

public interface AssociateCreatedMusicianUseCase {

    UuidHolderDTO execute(String bandUuid, String musicianCpf);
}
