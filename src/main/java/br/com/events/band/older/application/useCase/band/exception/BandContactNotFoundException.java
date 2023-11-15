package br.com.events.band.older.application.useCase.band.exception;

import br.com.events.band.older.infrastructure.exception.badRequest.NotFoundException;

/**
 * This exception is thrown when someone tries to find a band by uuid, but it does not exist on the database
 *
 * @author Gabriel Guiamrães de Almeida
 */
public class BandContactNotFoundException extends NotFoundException {
    public BandContactNotFoundException() {
        super(
                "Contato da banda não encontrada!",
                "Não foi encontrada um contato com o identificador informado."
        );
    }
}
