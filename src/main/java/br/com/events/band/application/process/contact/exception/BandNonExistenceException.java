package br.com.events.band.application.process.contact.exception;

import br.com.events.band.infrastructure.exception.badRequest.NotFoundException;

public class BandNonExistenceException extends NotFoundException {
    public BandNonExistenceException() {
        super(
                "Banda não encontrada!",
                "Não foi encontrada uma banda com este identificador!"
        );
    }
}
