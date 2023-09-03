package br.com.events.band.application.process.exception;

import br.com.events.band.infrastructure.exception.badRequest.NotFoundException;

public class MusicNonExistenceException extends NotFoundException {
    public MusicNonExistenceException() {
        super(
                "Música não encontrada!",
                "Não foi encontrada uma música com este identificador!"
        );
    }
}
