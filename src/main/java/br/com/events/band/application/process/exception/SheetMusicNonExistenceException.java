package br.com.events.band.application.process.exception;

import br.com.events.band.infrastructure.exception.badRequest.NotFoundException;

public class SheetMusicNonExistenceException extends NotFoundException {
    public SheetMusicNonExistenceException() {
        super(
                "Partitura não encontrada!",
                "Não foi encontrada uma partitura com este identificador!"
        );
    }
}
