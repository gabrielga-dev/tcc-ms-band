package br.com.events.band.application.process.band.create.exception;

import br.com.events.band.infrastructure.exception.badRequest.NotFoundException;

/**
 * This exception is thrown when, at event creation feature, the location is not found
 *
 * @author Gabriel Guimarães de Almeida
 */
public class CreateBandLocationDoesntExistsException extends NotFoundException {

    public CreateBandLocationDoesntExistsException() {
        super(
            "Endereço inválido!",
            "Por favor, selecione um endereço válido."
        );
    }
}
