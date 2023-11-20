package br.com.events.band.newer.adapter.port.rest.config.exception.bad_request;

import br.com.events.band.newer.core.exception.BusinessException;
import org.springframework.http.HttpStatus;

/**
 * This exception will be extended by any other exception that needs to return a 422 http status
 *
 * @author Gabriel Guimarães de Almeida
 */
public class UnprocessableEntityException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public UnprocessableEntityException(
        final String message, final String description
    ) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, HttpStatus.UNPROCESSABLE_ENTITY.value(), message, description);
    }
}
