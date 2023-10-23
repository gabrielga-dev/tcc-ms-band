package br.com.events.band.infrastructure.useCase.musician;

public interface DeleteMusiciansUseCase {

    void execute(String bandUuid, String musicianUuid);
}
