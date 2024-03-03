package br.com.events.band.business.use_case.music;

import br.com.events.band.data.io.music.response.MusicResponse;

import java.util.List;

public interface FindBandMusicsUseCase {

    List<MusicResponse> execute(String bandUuid);
}
