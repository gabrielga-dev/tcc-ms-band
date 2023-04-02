package br.com.events.band.application.process.band.exception;

import br.com.events.band.infrastructure.exception.badRequest.NotFoundException;

/**
 * This exception is thrown when, at event creation feature, the location is not found
 *
 * @author Gabriel Guimarães de Almeida
 */
public class BandLocationDoesntExistsException extends NotFoundException {

    public BandLocationDoesntExistsException() {
        super(
            "Endereço inválido!",
            "Por favor, selecione um endereço válido."
        );
    }
}
