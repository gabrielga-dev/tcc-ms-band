package br.com.events.band.core.exception;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.BadRequestException;

public class ResourceAlreadyActivatedException extends BadRequestException {

    public ResourceAlreadyActivatedException() {
        super("Recurso já ativado!", "Não se pode ativar este recurso pois ele já está ativado!");
    }
}
