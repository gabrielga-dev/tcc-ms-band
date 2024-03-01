package br.com.events.band.adapter.repository;

public interface BandMusicianRepository {

    void disassociateMusicianFromBand(String bandUuid, String musicianUuid);

    void disassociateMusicianFromBands(String musicianUuid);
}
