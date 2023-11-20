package br.com.events.band.core.exception;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.BadRequestException;

public class ResourceAlreadyDeactivatedException extends BadRequestException {

    public ResourceAlreadyDeactivatedException() {
        super("Recurso já desativado!", "Não se pode desativar este recurso pois ele já está desativado!");
    }
}
