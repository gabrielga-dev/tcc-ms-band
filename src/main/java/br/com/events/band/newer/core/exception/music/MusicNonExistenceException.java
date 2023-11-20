package br.com.events.band.newer.core.exception.music;

import br.com.events.band.newer.adapter.port.rest.config.exception.bad_request.NotFoundException;

public class MusicNonExistenceException extends NotFoundException {
    public MusicNonExistenceException() {
        super(
                "Música não encontrada!",
                "Não foi encontrada uma música com este identificador!"
        );
    }
}
