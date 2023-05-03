package br.com.events.band.application.process.exception;

import br.com.events.band.infrastructure.exception.badRequest.NotFoundException;

public class MusicianDoesNotExistException extends NotFoundException {
    public MusicianDoesNotExistException() {
        super(
                "Músico não encontrado!",
                "Não foi possível encontrar um músico com a credencial informada!"
        );
    }
}
