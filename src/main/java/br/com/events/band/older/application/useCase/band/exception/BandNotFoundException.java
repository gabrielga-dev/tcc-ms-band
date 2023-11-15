package br.com.events.band.older.application.useCase.band.exception;

import br.com.events.band.older.infrastructure.exception.badRequest.NotFoundException;

/**
 * This exception is thrown when someone tries to find a band by uuid, but it does not exist on the database
 *
 * @author Gabriel Guiamrães de Almeida
 */
public class BandNotFoundException extends NotFoundException {
    public BandNotFoundException() {
        super(
                "Banda não encontrada!",
                "Não foi encontrada uma banda com o identificador informado."
        );
    }
}
