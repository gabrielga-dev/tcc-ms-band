package br.com.events.band.business.use_case.musician;

import br.com.events.band.data.io.musician.response.MusicianWithAddressResponse;

public interface FindMusicianByCpfUseCase {

    MusicianWithAddressResponse execute(String musicianCpf);
}
