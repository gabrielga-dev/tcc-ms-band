package br.com.events.band.core.exception.music;

import br.com.events.band.adapter.port.rest.config.exception.bad_request.UnprocessableEntityException;

public class MusicianAlreadyLinkedToBandException extends UnprocessableEntityException {

    public MusicianAlreadyLinkedToBandException() {
        super(
                "Músico já ligado à banda!",
                "Não é possível associar este músico pois ele já pertence à banda"
        );
    }
}
