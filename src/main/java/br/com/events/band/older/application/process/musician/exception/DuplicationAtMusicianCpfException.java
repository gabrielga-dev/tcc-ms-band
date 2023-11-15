package br.com.events.band.older.application.process.musician.exception;

import br.com.events.band.older.infrastructure.exception.badRequest.BadRequestException;

public class DuplicationAtMusicianCpfException extends BadRequestException {

    public DuplicationAtMusicianCpfException(){
        super(
                "CPF já presente entre os músicos da banda.",
                "Já existe um músico com o CPF informado dentro da banda!"
        );
    }
}
