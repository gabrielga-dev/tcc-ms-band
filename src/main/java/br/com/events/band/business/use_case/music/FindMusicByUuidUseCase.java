package br.com.events.band.business.use_case.music;

import br.com.events.band.data.io.music.response.MusicResponse;

public interface FindMusicByUuidUseCase {
    MusicResponse execute(String uuid);
}
