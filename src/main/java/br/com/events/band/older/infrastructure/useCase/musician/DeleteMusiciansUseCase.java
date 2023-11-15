package br.com.events.band.older.infrastructure.useCase.musician;

public interface DeleteMusiciansUseCase {

    void execute(String bandUuid, String musicianUuid);
}
