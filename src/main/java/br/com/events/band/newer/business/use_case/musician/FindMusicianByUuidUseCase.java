package br.com.events.band.newer.business.use_case.musician;


import br.com.events.band.newer.data.io.musician.response.MusicianWithAddressResponse;

public interface FindMusicianByUuidUseCase {

    MusicianWithAddressResponse execute(String musicianUuid);
}
