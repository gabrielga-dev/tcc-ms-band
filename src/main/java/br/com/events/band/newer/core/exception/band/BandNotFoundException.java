package br.com.events.band.newer.core.exception.band;

import br.com.events.band.newer.adapter.port.rest.config.exception.bad_request.NotFoundException;

/**
 * This exception is thrown when someone tries to find a band by uuid, but it does not exist on the database
 *
 * @author Gabriel Guiamrães de Almeida
 */
public class BandNotFoundException extends NotFoundException {
    public BandNotFoundException() {
        super(
                "Banda não encontrada!",
                "Não foi encontrada uma banda com o identificador informado."
        );
    }
}
