package br.com.events.band.older.application.process.contact.exception;

import br.com.events.band.older.infrastructure.exception.badRequest.NotFoundException;

public class BandContactNonExistenceException extends NotFoundException {
    public BandContactNonExistenceException() {
        super(
                "Contato de banda não encontrado!",
                "Não foi encontrado um contato de banda com este identificador!"
        );
    }
}
