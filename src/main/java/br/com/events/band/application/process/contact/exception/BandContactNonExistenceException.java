package br.com.events.band.application.process.contact.exception;

import br.com.events.band.infrastructure.exception.badRequest.NotFoundException;

public class BandContactNonExistenceException extends NotFoundException {
    public BandContactNonExistenceException() {
        super(
                "Contato de banda não encontrado!",
                "Não foi encontrado um contato de banda com este identificador!"
        );
    }
}
