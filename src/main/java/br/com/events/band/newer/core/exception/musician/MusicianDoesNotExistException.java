package br.com.events.band.newer.core.exception.musician;

import br.com.events.band.older.infrastructure.exception.badRequest.NotFoundException;

public class MusicianDoesNotExistException extends NotFoundException {
    public MusicianDoesNotExistException() {
        super(
                "Músico não encontrado!",
                "Não foi possível encontrar um músico com a credencial informada!"
        );
    }
}
