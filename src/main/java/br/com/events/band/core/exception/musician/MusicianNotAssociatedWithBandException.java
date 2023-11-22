package br.com.events.band.core.exception.musician;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.UnprocessableEntityException;

public class MusicianNotAssociatedWithBandException extends UnprocessableEntityException {
    public MusicianNotAssociatedWithBandException() {
        super(
                "Músico não associado!",
                "O músico informado não está addociado com esta banda!"
        );
    }
}
