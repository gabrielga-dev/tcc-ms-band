package br.com.events.band.business.use_case.musician;

public interface DisassociateCreatedMusicianUseCase {

    void execute(String bandUuid, String musicianUuid);
}
