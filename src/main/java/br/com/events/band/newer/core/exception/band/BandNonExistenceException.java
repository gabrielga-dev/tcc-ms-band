package br.com.events.band.newer.core.exception.band;

import br.com.events.band.older.infrastructure.exception.badRequest.NotFoundException;

public class BandNonExistenceException extends NotFoundException {
    public BandNonExistenceException() {
        super(
                "Banda não encontrada!",
                "Não foi encontrada uma banda com este identificador!"
        );
    }
}
