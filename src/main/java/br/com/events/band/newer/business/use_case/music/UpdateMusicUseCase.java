package br.com.events.band.newer.business.use_case.music;

import br.com.events.band.newer.data.io.commom.UuidHolderDTO;
import br.com.events.band.newer.data.io.music.request.MusicRequest;

public interface UpdateMusicUseCase {

    UuidHolderDTO execute(String bandUuid, String musicUuid, MusicRequest request);
}
