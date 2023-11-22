package br.com.events.band.core.exception;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.ForbiddenRequestException;

public class CustomAccessDeniedException extends ForbiddenRequestException {

    public CustomAccessDeniedException() {
        super(
                "Ops! Você não pode realizar esta ação!",
                "Você não pode realizar esta ação pois não possui a permissão necessária."
        );
    }
}
