package br.com.events.band.core.exception.musician;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.NotFoundException;

public class MusicianDoesNotExistException extends NotFoundException {
    public MusicianDoesNotExistException() {
        super(
                "Músico não encontrado!",
                "Não foi possível encontrar um músico com a credencial informada!"
        );
    }
}
