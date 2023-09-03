package br.com.events.band.application.process.musician.exception;

import br.com.events.band.infrastructure.exception.badRequest.NotFoundException;

public class MusicianNotPresentOnBandException extends NotFoundException {

    public MusicianNotPresentOnBandException(){
        super(
                "Músico não encontrado!",
                "Não foi possível encontrar o músico na banda identificada."
        );
    }
}
