package br.com.events.band.core.exception.band;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.UnauthorizedRequestException;

/**
 * This exception is thrown when, at event creation feature, the location is not found
 *
 * @author Gabriel Guimarães de Almeida
 */
public class BandOwnerException extends UnauthorizedRequestException {

    public BandOwnerException() {
        super(
            "Você não pode fazer esta ação!",
            "Você não pode efetuar esta ação pois você não é o proprietário dela."
        );
    }
}
