package br.com.events.band.newer.business.use_case.musician;

import br.com.events.band.newer.data.io.musician.request.MusicianRequest;

public interface UpdateMusicianUseCase {

    void execute(String musicianUuid, MusicianRequest request);
}
