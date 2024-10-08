package br.com.events.band.core.exception.musician;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.NotFoundException;

public class MusicianExistsException extends NotFoundException {
    public MusicianExistsException() {
        super(
                "Músico já cadastrado!",
                "O músico informado já está cadastrado na plataforma! Tente achá-lo e vinculá-lo à sua banda!"
        );
    }
}
