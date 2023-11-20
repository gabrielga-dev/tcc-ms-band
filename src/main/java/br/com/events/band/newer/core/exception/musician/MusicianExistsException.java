package br.com.events.band.newer.core.exception.musician;

import br.com.events.band.newer.adapter.port.rest.config.exception.bad_request.NotFoundException;

public class MusicianExistsException extends NotFoundException {
    public MusicianExistsException() {
        super(
                "Músico já cadastrado!",
                "O músico informado já está cadastrado na plataforma! Tente achá-lo e vinculá-lo à sua banda!"
        );
    }
}
