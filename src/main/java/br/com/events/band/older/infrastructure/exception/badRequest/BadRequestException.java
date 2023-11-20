package br.com.events.band.older.infrastructure.exception.badRequest;

import br.com.events.band.newer.core.exception.BusinessException;
import org.springframework.http.HttpStatus;

/**
 * This exception will be extended by any other exception that needs to return a 400 http status
 *
 * @author Gabriel Guimarães de Almeida
 */
public class BadRequestException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(
        final String message, final String description
    ) {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), message, description);
    }
}
