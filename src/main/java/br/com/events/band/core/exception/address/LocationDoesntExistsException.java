package br.com.events.band.core.exception.address;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.NotFoundException;

/**
 * This exception is thrown when, at event creation feature, the location is not found
 *
 * @author Gabriel Guimarães de Almeida
 */
public class LocationDoesntExistsException extends NotFoundException {

    public LocationDoesntExistsException() {
        super(
            "Endereço inválido!",
            "Por favor, selecione um endereço válido."
        );
    }
}
