package br.com.events.band.business.use_case.musician_type;

import br.com.events.band.data.io.musician_type.response.MusicianTypeResponse;

import java.util.List;

public interface FindAllMusicianTypeUseCase {

    List<MusicianTypeResponse> execute();
}
