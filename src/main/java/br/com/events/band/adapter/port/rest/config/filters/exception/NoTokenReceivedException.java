package br.com.events.band.adapter.port.rest.config.filters.exception;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.UnauthorizedRequestException;

/**
 * This exception is called when a request with no api-ey is received
 *
 * @author Gabriel Guimarães de Almeida
 */
public class NoTokenReceivedException extends UnauthorizedRequestException {

    public NoTokenReceivedException() {
        super(
            "Usuário nao autenticado!",
            "Por favor, se autentique antes de tentar realizar esta ação!"
        );
    }
}
