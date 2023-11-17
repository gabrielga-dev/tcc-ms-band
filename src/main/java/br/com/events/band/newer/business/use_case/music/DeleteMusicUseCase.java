package br.com.events.band.newer.business.use_case.music;

public interface DeleteMusicUseCase {

    void execute(String bandUuid, String musicUuid);

}
