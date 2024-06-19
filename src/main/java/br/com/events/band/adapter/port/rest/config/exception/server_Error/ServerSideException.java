package br.com.events.band.adapter.port.rest.config.exception.server_Error;

import br.com.events.band.core.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ServerSideException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public ServerSideException(
        final String message, final String description
    ) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, description);
    }
}
