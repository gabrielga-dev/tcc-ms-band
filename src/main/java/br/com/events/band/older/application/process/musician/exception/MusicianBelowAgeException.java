package br.com.events.band.older.application.process.musician.exception;

import br.com.events.band.older.infrastructure.exception.badRequest.BadRequestException;

public class MusicianBelowAgeException extends BadRequestException {

    public MusicianBelowAgeException(Integer age){
        super(
                "Músico com idade abaixo do permitido!",
                "Um músico deve ter, pelo menos " + age + " anos de idade!"
        );
    }
}
