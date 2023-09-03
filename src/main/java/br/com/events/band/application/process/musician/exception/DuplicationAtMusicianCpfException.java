package br.com.events.band.application.process.musician.exception;

import br.com.events.band.infrastructure.exception.badRequest.BadRequestException;

public class DuplicationAtMusicianCpfException extends BadRequestException {

    public DuplicationAtMusicianCpfException(){
        super(
                "CPF já presente entre os músicos da banda.",
                "Já existe um músico com o CPF informado dentro da banda!"
        );
    }
}
