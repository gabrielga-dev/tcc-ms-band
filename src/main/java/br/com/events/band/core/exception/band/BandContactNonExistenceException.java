package br.com.events.band.core.exception.band;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.NotFoundException;

public class BandContactNonExistenceException extends NotFoundException {
    public BandContactNonExistenceException() {
        super(
                "Contato de banda não encontrado!",
                "Não foi encontrado um contato de banda com este identificador!"
        );
    }
}
