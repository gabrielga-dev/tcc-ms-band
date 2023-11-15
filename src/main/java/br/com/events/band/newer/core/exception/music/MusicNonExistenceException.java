package br.com.events.band.newer.core.exception.music;

import br.com.events.band.older.infrastructure.exception.badRequest.NotFoundException;

public class MusicNonExistenceException extends NotFoundException {
    public MusicNonExistenceException() {
        super(
                "Música não encontrada!",
                "Não foi encontrada uma música com este identificador!"
        );
    }
}
