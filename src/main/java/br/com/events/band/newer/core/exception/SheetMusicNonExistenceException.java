package br.com.events.band.newer.core.exception;

import br.com.events.band.older.infrastructure.exception.badRequest.NotFoundException;

public class SheetMusicNonExistenceException extends NotFoundException {
    public SheetMusicNonExistenceException() {
        super(
                "Partitura não encontrada!",
                "Não foi encontrada uma partitura com este identificador!"
        );
    }
}
